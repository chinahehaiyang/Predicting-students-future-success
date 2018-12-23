

function plotData(X, y)
  pos = find(y==1);
  neg = find(y==0);
  plot(X(pos, 1), X(pos, 2), 'kx', 'MarkerSize', 5)
  hold on;
  plot(X(neg, 1), X(neg, 2), 'ko', 'MarkerSize', 5, 'Color', 'r')
  
endfunction