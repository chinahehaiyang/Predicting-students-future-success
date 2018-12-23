%% Load Data
%  The first two columns contains the exam scores and the third column
%  contains the label.
%plotData( X, y);
%legend('Go to Sc10','not');
%xlabel('EllSci');
%ylabel('Math');
suc_data2 = load('suc_mark_round2.txt');  %load the data
X2 = suc_data2(:, 1:2); y2= suc_data2(:, 3);  %store it into x and y array

%m is the number of samples (students) and n is the features  (passed or fail Science 10)
[m2,n2]=size(X2);  
intialTheta2 = zeros((n2+1),1); %initially is set to 0, and will e determined by the optimization algorithm
[J2, grad2]=computeCost(intialTheta2, X2, y2); %the cost and the gradient

options2 = optimset('GradObj', 'on', 'MaxIter', 400); %set the options
%values of theta that will minimize cost function
theta2 =  fminunc(@(t)computeCost(t, X2, y2), intialTheta2, options2); 
%%plotDecision(theta2, X2, y2);
%%legend('Go to Sc10', 'Not');
%%xlabel('EllSci');
%%ylabel('Math');


%%prob2 = sigmoid([1 67 60] * theta2); % [ Sci and then Math] function that will compute prediction


%printf('For a student with Sc10 mark of  %d and Math mark of %d we predict an admission probability of %f%%', ScMark, MathMark, prob);
predictions2 = predict(theta2, X2);
accuracy2 = mean(double (predictions2 == y2) * 100);
