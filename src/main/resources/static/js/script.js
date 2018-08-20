function validateQuestionAndAnswer() {
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