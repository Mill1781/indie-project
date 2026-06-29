A = rand(6, 6);
B = rand(8, 6);
C = rand(5, 8);
rank_A = rank(A);
rank_B = rank(B);
rank_C = rank(C);
fprintf("rank of 6 * 6 matrix A =  %d\n", rank_A);
fprintf("rank of 8 * 6 matrix B =  %d\n", rank_B);
fprintf("rank of 5 * 6 matrix C =  %d\n", rank_C);

%(a) Explain 1 : As we know, rank can be the number of indenpdent vectors or dimensions
% in a matrix, and we know rank of column equals to rank of row, so basically the rank
% numbers r wouldn't exceed row m or column n in rank theroem.
% For example, a matrix A with m by n, we assume m > n and m >= r > n. By rank theroem, 
% m >= r and n >= r. Now we find a contridiction with m > r > n and n >= r, 
% and this matrix actually doesn't have enough column
% vector to form r independent vectors, so this assumption is a
% contridiction and need to have r <= n when m > n. With m > n and r <= n, we can
% conclude that r <= min(m, n). By symmetry, let m < n then we
% can still obtain the same result.
% ( r <= m & m < n ==> r <= min(m, n) )

%(a) Explain 2 : For random A(m by n), we usually would expect the rank
% equals to min(m, n). The rank number can be seen as the number of
% indepent vector(s) for row or column vector(s). For n Column vectors in 
% R^m space(m <= n), the possibility is very low for the vectors to lay on the
% same subspace(linear dependence) in common. In other words, it is more
% difficult to generate depedent vectors compared to indepdent vectors, the
% probability that any new vector falls into the subspace spanned by the 
% previous vectors (linear dependence) is nearly zero,
% so we can expect r = m. By symmetry(n <= m), we get r = n. 
% In summary, r = min(m, n) in random matrices for most of the cases.
