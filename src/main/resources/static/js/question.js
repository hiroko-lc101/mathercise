var testQuestion = document.getElementsByClassName('testQuestion');
var questionCount = testQuestion.length;
var testString;
var pos;
var questionText;
var answerText;
var questions = [];

for (i = 0; i < questionCount; i++) {
    testString = testQuestion[i].getAttribute('value');
    pos = testString.indexOf(',');
    questionText = testString.slice(0, pos);
    answerText = testString.slice(pos + 1);
    questions.push({"question": questionText, "answer": answerText});
}

//questions.push({"question": "1+0", "answer": "1"});
//questions.push({"question": "2+0", "answer": "2"});
//questions.push({"question": "3+0", "answer": "3"});
