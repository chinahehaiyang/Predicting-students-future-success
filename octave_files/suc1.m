%% Load Data
%  The first two columns contains the exam scores and the third column
%  contains the label.
%plotData( X, y);
%legend('Go to Sc10','not');
%xlabel('EllSci');
%ylabel('Math');
suc_data1 = load('suc_mark_round1.txt');  %load the data
X1 = suc_data1(:, 1:2); y1= suc_data1(:, 3);  %store it into x and y array

%m is the number of samples (students) and n is the features  (passed or fail Science 10)
[m1,n1]=size(X1);  
intialTheta1 = zeros((n1+1),1); %initially is set to 0, and will e determined by the optimization algorithm
[J1, grad1]=computeCost(intialTheta1, X1, y1); %the cost and the gradient

options1 = optimset('GradObj', 'on', 'MaxIter', 400); %set the options
%values of theta that will minimize cost function
theta1 =  fminunc(@(t)computeCost(t, X1, y1), intialTheta1, options1); 
%%plotDecision(theta1, X1, y1);
%%legend('Go to Sc10', 'Not');
%%xlabel('EllSci');
%%ylabel('Math');


%%prob1 = sigmoid([1 67 60] * theta1); % [ Sci and then Math] function that will compute prediction


%printf('For a student with Sc10 mark of  %d and Math mark of %d we predict an admission probability of %f%%', ScMark, MathMark, prob);
predictions1 = predict(theta1, X1);
accuracy1 = mean(double (predictions1 == y1) * 100);