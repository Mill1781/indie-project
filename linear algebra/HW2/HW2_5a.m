
A = floor(10 * rand(6));
while det(A) == 0 % which means A is singular and x hasno unique solution
    A = floor(10 * rand(6)); % regenerate
end
b = floor(20 * rand(6, 1)) - 10;
x = A \ b; % Ax = B
% Now we got the solution of Ax = b system

% R is the reduced row echelon form matrix
R = rref([A b]);
fprintf("solution x:\n");
disp(x);
fprintf("seventh column of reduced row echelon form matrix:\n");
disp(R(:,7));

% when the difference of x and seventh column of R is almost the same print
% equal, the precision is 10 ^ -4
if ( all(abs(x - R(:, 7)) <= (10 ^ (-4))))
    fprintf("two vectors equal\n");
elseif ( all(abs(x - R(:, 7)) <= (10 ^ (-2))))
    fprintf("very close\n");
else
    fprintf("not the same, error occured\n");
end

% ------------------------------------
% Explanation

% for the reason why two vectors are the same, we know that row operation
% would not influence the linear relationship of column vectors in the matrix
% so we can separate the augmented matrix to original form A and b, and we can find that A
% matrix become a identity matrix, so x vector multiply I = x, which means
% I * x = b' --> x = b'