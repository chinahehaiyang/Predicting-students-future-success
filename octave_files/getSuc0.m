%after all of the thetas are minimized, it is very easy to calculate the probabilities
%find the probability of the student to have success level of 3 or above
suc_prob0 = probSc(ScMark, MathMark, theta0); 
%find the probability of the student to have success level of 4 (round1)
suc_prob1 = probSc(ScMark, MathMark, theta1);
%find the probability of the student to have success level of 2 (round2)
suc_prob2 = probSc(ScMark, MathMark, theta2);