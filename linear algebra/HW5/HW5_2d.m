X = rand(8, 2);
Y = rand(6, 2);
A = X * Y';
disp(A);
rank_A = rank(A);
fprintf("the rank of A = %d\n", rank_A);