
function [J, grad] = computeCost (Theta, X, y)
  m = size(X, 1); % number of training example
  X = [ones(m,1) X]; %bias term
  h=sigmoid(X* Theta); %compute the sigmoid function
  J= (-1/m) * sum(y .* log(h) + (1-y) .* log(1-h) ); %the formula for cost function
  grad = zeros(size(Theta, 1),1);
  for i=1: size(grad)
    grad(i)= (1/m) *sum( (h-y)' *X(:, i));  %the formula for gradient
   end
  
endfunction
