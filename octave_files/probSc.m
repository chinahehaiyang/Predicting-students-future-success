function prob = probSc (ScM, MathM, theta)
  ScMark=ScM;
  MathMark = MathM;
  theta=theta;
  prob = sigmoid([1 ScMark MathMark] * theta); %calls the sigmoid function to get the probability
endfunction
