A = floor(10 * rand(6));
while det(A) == 0 % which means A is singular and x hasno unique solution
    A = floor(10 * rand(6)); % regenerate
end
% now let change A into singular matrix
A(:, 3) = A(:, 1 : 2) * [4 3]';
b = floor(20 * rand(6, 1)) - 10;
R = rref([A b]);
disp(R);
fprintf("Normaly, this system would have no solutions\n");


% ------------------------------------
% Explanation

% We change third column of A into the linear combination of fisrt and
% second columns combination of A, so the matrix must be singular and the
% solution of x has infinite solutions when vector b is in the column
% space of A. In other words, b is the linear combination of A's columns 
% vectors so this system is consistent(b is ramdomly generated, so this 
% posibility is very small). Otherwise, x has no solutions.
% In summary, this system is consistent and A is singular, so it would only
% have infinite solutions. This point can be simply found by observing 
% rref([A b]) itself.
