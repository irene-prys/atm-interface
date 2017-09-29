let initNavigation = function () {
    let basicFunctionality = basicAtmFunctions();

    initButtons();

    function initButtons() {
        initBalanceBtn();
        initWithdrawBtn();
        initExitBtn();
    }

    function initBalanceBtn() {
        let exitBtn = document.body.querySelector(".balance-button");
    }

    function initWithdrawBtn() {
        let exitBtn = document.body.querySelector(".withdraw-button");
    }

    function initExitBtn() {
        let exitBtn = document.body.querySelector(".exit-button");
        exitBtn.onclick = function() {
            basicFunctionality.clearHistoryAndGoToIndex();
        }
    }
};

initNavigation();