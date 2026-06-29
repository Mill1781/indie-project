% decide n_1, n_2, n_3
global n; % declare global variable
n = input("please input matrix length n : ");
m = input("please input tile(s) length m : ");
n_1 = n;
n_2 = n;
n_3 = n;
A_global = round(100 * rand(n_1, n_2));
B_global = round(100 * rand(n_2, n_3));
[global_read_count, C_global] = tiled_multiply(A_global, B_global, m);
fprintf("read count = %d\n, C_global = \n", global_read_count);
disp(C_global);


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

