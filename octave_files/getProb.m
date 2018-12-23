probData = load('probability.txt'); %load probability.txt
ScMark = probData(1,1); %science mark is the first number
MathMark = probData(1,2); %math mark is the second number
prob = probSc(ScMark, MathMark, theta); %find the probability of the student going into science 10
