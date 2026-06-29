% decide n_1, n_2, n_3
global n; % declare global variable
n = input("please input matrix length n : ");
n_1 = n;
n_2 = n;
n_3 = n;
A_global = round(100 * rand(n_1, n_2));
B_global = round(100 * rand(n_2, n_3));
[global_read_count, C_global] = naive_multiply(A_global, B_global);
fprintf("read count = %d\n, C_global = \n", global_read_count);
disp(C_global);


% the func to get global_count and product A and B   stored in C matrix
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

