var currentQuestion = 0;
var correctCount = 0;
var questionCount = questions.length;
var container = document.getElementById('quizContainer');
var questionElement = document.getElementById('question');
//var nextButton = document.getElementById('nextButton');
var resultContent = document.getElementById('result');

function loadQuestion(questionIndex) {
    var q = questions[questionIndex];
    questionElement.textContent = q.question;
}

function loadNextQuestion() {
    var answer = document.getElementById('answer');
    if (questions[currentQuestion].answer == answer.value) {
        correctCount += 1;
    }
    answer.value = "";
    answer.focus();
    currentQuestion++;
//    if (currentQuestion == questionCount - 1) {
//        nextButton.textContent = "Finish";
//    }
    if (currentQuestion == questionCount) {
        container.style.display = 'none';
        resultContent.style.display = '';
        resultContent.textContent = "Score: " + correctCount / questionCount * 100 + "%";
        return;
    }
    loadQuestion(currentQuestion);
}

loadQuestion(currentQuestion);