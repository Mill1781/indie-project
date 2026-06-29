%generate two random 5 by 5 matrices A, B
A = round(10 * rand(5));
B = round(10 * rand(5));

%generate three dimension array to store the operation of A and B
A_array = zeros(5, 5, 4);

% question (a)
fprintf("question (a)\n");
A_array(:,:,1) = A * B;
A_array(:,:,2)= B * A;
A_array(:,:,3)= (A' * B')';
A_array(:,:,4)= (B' * A')';

% I wrote a function in the bottom of the code, please have a look
is_euqal(A_array);
fprintf("\n");


% question (b)

fprintf("question (b)\n");
A_array(:,:,1) = A * B';
A_array(:,:,2)= A' * B;
A_array(:,:,3)= (B * A')';
A_array(:,:,4)= (B' * A)';


is_euqal(A_array);
fprintf("\n");

% question (c)

fprintf("question (c)\n");
A_array(:,:,1) = inv(A * B);
A_array(:,:,2)= inv(A) * inv(B);
A_array(:,:,3)= inv(B * A);
A_array(:,:,4)= inv(B) * inv(A);

is_euqal(A_array);
fprintf("\n");


% question (d)

fprintf("question (d)\n");
A_array(:,:,1) = inv(A * B');
A_array(:,:,2)= inv(A) * inv(B)';
A_array(:,:,3)= inv(B)' * inv(A);
A_array(:,:,4)= (inv(A' * B))';


is_euqal(A_array);
fprintf("\n");




% function to test whether two matrices are equal
% if two matrices equal, print them out
function is_euqal(Array)
% if no matrices equal to each other then key = 0, key = 1 for any equivalent pair exists
    key = 0;
    for i = 1 : 3
        for j = i + 1 : 4
            % in order to avoid the small error by double data type auto rounding
            if all(abs(Array(:,:,j) - Array(:,:,i)) < 10 ^ -6, 'all')
                fprintf("A_%d = A_%d are equal\n", i, j);
                % it is feasible to apply isequal() funciton to tell
                % matrices' differnce as well
                key = 1;
            end
        end
    end
    % when no matirces are equivalent
    if key == 0
        fprintf("no matrices are equal in this section");
    end

end