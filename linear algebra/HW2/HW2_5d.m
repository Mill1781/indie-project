A = floor(10 * rand(6));
while det(A) == 0 % which means A is singular and x hasno unique solution
    A = floor(10 * rand(6)); % regenerate
end
% now let change A into singular matrix
A(:, 3) = A(:, 1 : 2) * [4 3]';

y = floor(20 * rand(6, 1)) -10;
c = A * y;
R = rref([A c]);
% by observing the rref matrix we can find solution x when x_3 = 0
% x_1, x_2, x_3 don't have the reletionship like c * x_1 = x_3
w = [R(1, 7), R(2, 7), 0 ,R(3, 7), R(4, 7), R(5, 7)]';

fprintf("vector Aw and c:\n");
if(isequal(A*w, c))
   fprintf("A*w equals to c\n") 
end
disp([(A*w) c]);
fprintf("residual vector c - Aw:\n");
disp(c - A*w);


