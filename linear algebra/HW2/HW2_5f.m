A = floor(10 * rand(6));
while det(A) == 0 % which means A is singular and x hasno unique solution
    A = floor(10 * rand(6)); % regenerate
end
% now let change A into singular matrix
A(:, 3) = A(:, 1 : 2) * [4 3]';

y = floor(20 * rand(6, 1)) -10;
c = A * y;
R = rref([A c]);
w = [R(1, 7), R(2, 7), 0 ,R(3, 7) R(4, 7), R(5, 7)]';
x_3 = 1;
x_1 = -4 * x_3;
x_2 = -3 * x_3;
z = [x_1, x_2, x_3, 0, 0, 0]';

v = w + 3 * z;
fprintf("residual vector c - A*v:\n");
disp(c - A*v);
fprintf("solution v\n:");
disp(v);
fprintf("the value of x_3 is %d\n", v(3,1));

