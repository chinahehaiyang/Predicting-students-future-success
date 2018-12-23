clear ; close all; clc; 

data = load('data_mark.txt');  %load the data
X = data(:, 1:2); y = data(:, 3);  %store it into x and y array

%m is the number of samples (students) and n is the features  (passed or fail Science 10)
[m,n]=size(X);  
intialTheta = zeros((n+1),1); %initially is set to 0, and will be determined by the optimization algorithm
[J, grad]=computeCost(intialTheta, X, y); %the cost and the gradient

options = optimset('GradObj', 'on', 'MaxIter', 400); %set the optimization options
%fminunc parameter will minimize the cost function 
%(the J and grad (gradient) will be calculated in the computeCost class, since fminunc needs J and grad)
theta =  fminunc(@(t)computeCost(t, X, y), intialTheta, options); 
predictions = predict(theta, X); %required to calculate the accuracy
accuracy = mean(double (predictions == y) * 100);
