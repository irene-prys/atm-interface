let initBalanceScreenNavigation = function () {
    let basicFunctionality = basicAtmFunctions();

    initButtons();

    function initButtons() {
        initExitButton();
        initBackButton();
    }

    function initExitButton() {
        let balanceExitBtn = document.body.querySelector(".balance-exit-button");
        balanceExitBtn.onclick = function() {
            basicFunctionality.clearHistoryAndGoToIndex();
        };
    }

    function initBackButton() {
        let balanceBackBtn = document.body.querySelector(".balance-back-button");
        balanceBackBtn.onclick = function() {
             window.history.back();
        };
    }
};

initBalanceScreenNavigation();