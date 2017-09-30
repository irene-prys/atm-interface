let initOperationsScreenNavigation = function () {
    let basicFunctionality = basicAtmFunctions();

    initButtons();

    function initButtons() {
        initBalanceBtn();
        initWithdrawBtn();
        initExitBtn();
    }

    function initBalanceBtn() {
        let balanceBtn = document.body.querySelector(".balance-button");
        balanceBtn.onclick = function() {
            location.href='/operations/balance';
        };
    }

    function initWithdrawBtn() {
        let withdrawBtn = document.body.querySelector(".withdraw-button");
        withdrawBtn.onclick = function() {
            location.href='/operations/withdraw';
        };
    }

    function initExitBtn() {
        let exitBtn = document.body.querySelector(".exit-button");
        exitBtn.onclick = function() {
            basicFunctionality.clearHistoryAndGoToIndex();
        };
    }
};

initOperationsScreenNavigation();