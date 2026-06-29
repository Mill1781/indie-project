A = round(9 * rand(10, 7)) + 1;
B = round(9 * rand(8, 12)) + 1;
C = round(9 * rand(10, 15)) + 1;

rank_A = rank(A);
rank_B = rank(B);
rank_C = rank(C);

fprintf("rank of 10 * 7 matrix A =  %d\n", rank_A);
if(min(10, 7) == rank_A)
    fprintf("A has full rank\n");
else
    fprintf("A is rank deficient\n");
end

fprintf("rank of 8 * 12 matrix B =  %d\n", rank_B);
if(min(8, 12) == rank_B)
    fprintf("B has full rank\n");
else
    fprintf("B is rank deficient\n");
end
fprintf("rank of 10 * 15 matrix C =  %d\n", rank_C);
if(min(10, 15) == rank_C)
    fprintf("C has full rank\n");
else
    fprintf("C is rank deficient\n");
end
