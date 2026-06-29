% (a)
W = triu(ones(4));
x = [1:4]';
A = [[0 1 0 0]',[0 0 1 0]',[5 4 3 2]', [1 2 3 4]'];
fprintf("Question a\nL(W) = WA, A = \n");
disp(A);

% (b)
y = inv(W)*x;
fprintf("Question b\ny = \n");
disp(y)

% (c)
z = A * y;
fprintf("Question c\nz = \n");
disp(z);

% (d)
Ax = W*z;
fprintf("Question d\nL(x) = \n");
disp(Ax);