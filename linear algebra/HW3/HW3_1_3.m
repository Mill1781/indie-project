% decide n_1, n_2, n_3
global n; % declare global variable

% task 1
n = 128;
n_1 = n;
n_2 = n;
n_3 = n;
A_global = round(100 * rand(n_1, n_2));
B_global = round(100 * rand(n_2, n_3));

% task 2
[naive_global_read_count, C_naive] = naive_multiply(A_global, B_global);

% task 3
[tiled_global_read_count, C_tiled] = tiled_multiply(A_global, B_global, 4);

% task 4
C_right = A_global * B_global;
% determine the errors of two methods by rms
fprintf("the errors of naive multiplication form root mean square(rms) : %e\n", rms(C_naive - C_right, "all"));
fprintf("the errors of tiled multiplication form root mean square(rms) : %e\n", rms(C_tiled - C_right, "all"));

% task 5
m_x = [1, 2, 4, 8, 16, 32, 64];
count_y = zeros(7, 1);
rms_naive = zeros(7, 1); % naive method rms for discussion, not task 5 requirement
rms_naive(:, 1) = rms(C_naive - C_right, "all");
rms_tiled = zeros(7, 1); % tiled method rms

for i = 1 : 7
    [count_y(i), C_tiled] = tiled_multiply(A_global, B_global, m_x(i));
    rms_tiled(i) = rms(C_tiled - C_right, "all");
end

% plotting part
figure;
plot(m_x, count_y,"-o", 'LineWidth', 2);%"-o" means to mark circle o on every single data point
grid on;
xlabel("tile length m");
xticks(m_x); % mark the data on x axis
ylabel("global memory count(s)");
legend("tiled multiplication");
title("global memory read count versus m");

% another plotting about rms, not the task 5 itself
figure;
plot(m_x, rms_naive,"-or", 'LineWidth', 2); % naive line is red line
hold on;
plot(m_x, rms_tiled,"--ob", 'LineWidth', 2); % tiled line is blue dot line
grid on;
xlabel("tile length m");
xticks(m_x); % mark the data on x axis
ylabel("rms errors");
legend("rms of naive multiplication", "rms of tiled multiplication");
title("rms errors versus m");




% discussion part
% (1), for the question whether C_naive and C_tiled are accurate. 
% From the numerical and plotting results, we can see both naive_multiply and tiled_multiply
% are extremely accurate with both rms = 0
% (2), about the interpretation about the count versus m plot, we can
% find that the counts decrease when length m doulbes. With this fact,
% I have a conclusion is that when the m is larger, the reading efficiency
% enhances. We can find this property with single tile as well. To
% calculate the read counts from global memory, we have m by m tile, counts
% by naive method is 2 (m*m). However, counts by tiled
% method is merely 2 * m = 2 * m since the data in shared memory are reused. 
% the sum count number of two methods
% are 2 * m^2 * (k1 * k2) and 2 * m * (k1 * k2) --> rewrite as 2 * n^2
% and 2 * ( (n^2) / m), which is clear that tiled one is more efficient, and
% when m increase the counts being smaller.
% p.s. k1 = k2 = n/m

% p.s. extra thinking. Although tiled multiply is better, it also means it
% need bigger shared memory space to store those temporary data and
% in common, more efficent memory are more expensive and small, so it is still
% a good method to calculate but not always the best one depending on the
% situation.









% the naive func to get global_count and product A and B   stored in C matrix
function [count, C] = naive_multiply(A_global, B_global)
    global n;
    count = 0;
    C = zeros(n, n);
    a_i_shared = zeros(1, n);
    b_j_shared = zeros(n, 1);
    %calculation by entries and record the read count from global memory
    for i = 1 : n
        for j = 1 : n
           %read a_i and b_i from global memory
           a_i_shared = A_global(i,:);
           b_j_shared = B_global(:, j);
           c_ij_shared = a_i_shared * b_j_shared;
           count = count + 2;
           C(i, j) = c_ij_shared;
        end
    end
    %return matrix C back to C_global memory and read count(s)
end


% the func to get global_count and product A and B   stored in C matrix
function [count, C] = tiled_multiply(A_global, B_global, m)
    global n;
    count = 0;
    C = zeros(n, n);
    %compute tile(s)
    k_1 = n/m; % Row of tile(s)
    k_3 = n/m; % Column tile(s)
    for i = 1 : k_1
        for j = 1 : k_3
            %read global A and B
            %for A, read row(A) from row R( m * (i - 1) ) + R (1 to m * i )
            a_i_shared = A_global( m * (i - 1) + 1 : m * i, :);
            %for B, read row(B) from row C( m * (j - 1) ) + C (1 to m * j )
            b_i_shared = B_global( : , m * (j - 1) + 1 : m * j);
            %record the global read count
            count = count + 2 * m;
            %compute the product then store in tile (i, j) of C
            c_ij_shared = a_i_shared * b_i_shared;
            C(m * (i - 1) + 1 : m * i, m * (j - 1) + 1 : m * j) = c_ij_shared;
        end
    end
    %return matrix C back to C_global memory and read count(s)
end