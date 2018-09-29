function validateQuestionAndAnswer() {
    // Card - add.html: user input check
    var strQuestion = document.forms["Card"]["question"].value;
    var strAnswer = document.forms["Card"]["answer"].value;
    var pattern1 = /[0-9][+-][0-9]/;
    var pattern2 = /[+-]/;
    var opPos;
    var firstNum;
    var secondNum;
    var ans;

    if (strQuestion.search(pattern1) == -1) {
        alert('Question must contain numbers and one operator ("+" or "-")');
        return false;
    }

    opPos = strQuestion.search(pattern2);
    firstNum = strQuestion.slice(0, opPos);
    secondNum = strQuestion.slice(opPos + 1);
    if (strQuestion.charAt(opPos) == "+") {
        ans = Number(firstNum) + Number(secondNum);
    } else {
        ans = Number(firstNum) - Number(secondNum);
    }

    if (ans != strAnswer) {
        alert("Answer is incorrect");
        return false;
    }
}

function processKeyPress(e) {
    // Assessment - assess.html: Enter key press
    if (e.keyCode == 13) {
        loadNextQuestion();
    }
    else {
        return false;
    }
}

function addTestQuestion() {
    // Test - edit.html: Adding a test question to a Fitness Test
    var tblBody = document.getElementById('testQuestionContainer').tBodies[1];
    var newNode = tblBody.rows[0].cloneNode(true);
    tblBody.appendChild(newNode);

    var questionNumber = document.getElementsByClassName('questionNumber');
    questionNumber[questionNumber.length - 1].innerHTML = questionNumber.length;

    var selected = document.getElementsByName('cardIds');
    selected[questionNumber.length - 1].value = -1;

    var removeTestQuestion = document.getElementsByClassName('removeTestQuestion');
    removeTestQuestion[removeTestQuestion.length - 1].href = 'javascript:removeTestQuestion(' + (removeTestQuestion.length - 1) + ')';
}

function removeTestQuestion(questionNumber) {
    // Test - edit.html: Removing a test question from a Fitness Test
    var questionNumbers = document.getElementsByClassName('questionNumber');
    if (questionNumbers.length == 1) {
        alert("Fitness Test must have at least one question.");
    }
    else {
        var tblBody = document.getElementById('testQuestionContainer').tBodies[1];
        var removeNode = tblBody.rows[questionNumber];
        tblBody.removeChild(removeNode);

        // renumber question number
        var i;
        for (i = 0; i < questionNumbers.length; i++) {
            questionNumbers[i].innerHTML = i + 1;
        }

        var removeTestQuestion = document.getElementsByClassName('removeTestQuestion');
        for (i = 0; i < questionNumbers.length; i++) {
            removeTestQuestion[i].href = 'javascript:removeTestQuestion(' + i + ')';
        }
    }
}

function checkCardValues() {
    var cardIds = document.getElementsByName('cardIds');
    var i;
    var questionNumber = -1;
    for (i = 0; i < cardIds.length; i++) {
        if (cardIds[i].value == -1) {
            if (questionNumber == -1) {
                questionNumber = i;
            }
        }
    }
    if (questionNumber != -1) {
        alert("Please select a Flash Card for Question Number " + (questionNumber + 1));
        return false;
    }
}