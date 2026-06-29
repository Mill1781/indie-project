x = round(9 * rand(8, 1)) + 1;
y = round(9 * rand(6, 1)) + 1;
A = x * (y');
rank_A = rank(A);
disp(A);
fprintf("the rank of A = %d\n", rank_A);