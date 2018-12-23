suc_data0 = load('suc_mark.txt');  %load the data
X0 = suc_data0(:, 1:2); y0= suc_data0(:, 3);  %store it into x and y array

%m is the number of samples (students) and n is the features  (passed or fail Science 10)
[m0,n0]=size(X0);  
intialTheta0 = zeros((n0+1),1); %initially is set to 0, and will e determined by the optimization algorithm
[J0, grad0]=computeCost(intialTheta0, X0, y0); %the cost and the gradient

options0 = optimset('GradObj', 'on', 'MaxIter', 400); %set the options
%values of theta that will minimize cost function
theta0 =  fminunc(@(t)computeCost(t, X0, y0), intialTheta0, options0);
