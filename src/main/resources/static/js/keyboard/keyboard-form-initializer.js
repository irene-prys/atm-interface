'use-strict'

let applyAtmInputSettings = function () {
    let CARD_NUMBER_SEGMENT_SIZE = 4;
    let basicFunctionality = basicAtmFunctions();
    document.addEventListener("DOMContentLoaded", ready);

    function ready() {
        applySettings();
    }

    function applySettings() {
        applySettingsToCardNumberComponents();
        applySettingsForClearButtons();
        applySettingsToPinCodeComponents();
        applySettingsToCancelButton();
        applyFormSettings();
    }

    function applySettingsForClearButtons() {
        let clearButtons = document.body.querySelectorAll('.clear-keyboard-form-input');
        for(let i = 0; i < clearButtons.length; i++) {
            clearButtons[i].onclick = function() {
                let inputElement = document.getElementById(clearButtons[i].getAttribute("for"));
                if(inputElement) {
                    inputElement.value = "";
                    invalidateSendButton(inputElement);
                }
            }
        }
    }

    function applySettingsToCardNumberComponents() {
        let atmCardNumberComponents = document.body.querySelectorAll('.atm-card-number');
        for(let i = 0; i < atmCardNumberComponents.length; i++) {
            let inputElement = atmCardNumberComponents[i];
            invalidateSendButton(inputElement);
            inputElement.addEventListener('textChanged', function (e) {
                invalidateSendButton(inputElement);
                if(isSeparatorNeeded(inputElement.value.length, inputElement.minLength)) {
                    inputElement.value += '-';
                }
            });
        }
    }

    function applySettingsToCancelButton() {
        let cancelBtns = document.body.querySelectorAll(".atm-cancel-btn");
        for(let i = 0; i < cancelBtns.length; i++) {
            if(cancelBtns[i]) {
                cancelBtns[i].onclick = function() {
                    basicFunctionality.clearHistoryAndGoToIndex();
                };
            }
        }
    }

    function applySettingsToPinCodeComponents() {
        let atmCardNumberComponents = document.body.querySelectorAll('.atm-card-pin');
        for(let i = 0; i < atmCardNumberComponents.length; i++) {
            let inputElement = atmCardNumberComponents[i];
            invalidateSendButton(inputElement);
            inputElement.addEventListener('textChanged', function (e) {
                invalidateSendButton(inputElement);
            });
        }
    }

    function applyFormSettings(){
        let forms = document.body.querySelectorAll('.atm-auth-form');
        for(let i = 0; i < forms.length; i++) {
            forms[i].onsubmit = function () {
                basicFunctionality.clearHistory();
            };
        }
    }

    function isSeparatorNeeded(currentLength, minLength) {
        if(minLength !== -1 && currentLength >= minLength) {
            return false;
        }

        let countOfSegments = Math.floor(currentLength/CARD_NUMBER_SEGMENT_SIZE);
        let countOfSeparators = currentLength%CARD_NUMBER_SEGMENT_SIZE;
        return countOfSeparators + 1 === countOfSegments;
    }

    function invalidateSendButton(inputElement) {
        let keyboardGroupContainer = inputElement.closest(".keyboard-group-container");
        let sendBtnElement = keyboardGroupContainer.querySelector(".keyboard-form-send-btn");
        let disable = inputElement.minLength !== -1 && inputElement.value.length < inputElement.minLength;// if input is full then disable
        sendBtnElement.disabled = disable;
    }
};

applyAtmInputSettings();