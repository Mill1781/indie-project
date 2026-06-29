A = floor(10 * rand(6));
while det(A) == 0 % which means A is singular and x hasno unique solution
    A = floor(10 * rand(6)); % regenerate
end
% now let change A into singular matrix
A(:, 3) = A(:, 1 : 2) * [4, 3]';
R = rref([A zeros(6, 1)]);

% we can actually find this is homogeneous system, and all solutions would 
% let Ax become zero matrix.
% Observing RREF, we can see x_1 = -4 * x_3, x_2 = -3 * x_3, since 
% third column = 4 * first column + 3 * second column (liear combination)
x_3 = 1;
x_1 = -4 * x_3;
x_2 = -3 * x_3;
z = [x_1, x_2, x_3, 0, 0, 0]';
fprintf("A * z :\n");
disp(A*z);