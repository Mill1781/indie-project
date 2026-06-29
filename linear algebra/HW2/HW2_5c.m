A = floor(10 * rand(6));
while det(A) == 0 % which means A is singular and x hasno unique solution
    A = floor(10 * rand(6)); % regenerate
end
% now let change A into singular matrix
A(:, 3) = A(:, 1 : 2) * [4 3]';

y = floor(20 * rand(6, 1)) -10;
c = A * y;
R = rref([A c]);
disp(R);
fprintf("this system has infinite solutions\n");

% ------------------------------------
% Explanation

% it is clear that y exist and Ay = c, so we can easily see for Ax = c,
% this system at least must have the solution x = y, and this system Ax = b 
% must be consistent
% For how many solutons this system has, we should look at matrix A. When
% A is singular, which means A has redundent column(s) and x will have
% free variable(s) and this system will have infinite solutions.
% On the contrary, when A is nonsingular, c is the only combination of
% A's columns, so we can find x has only one solution by equation
% A' * Ax = A' * c --> x = A' * c
% From the reasons above, A become singular at section (b) and this system
% has infinite solutions. To be specific, x_3 is the free variable.

