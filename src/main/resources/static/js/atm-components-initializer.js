'use-strict'

let applyAtmInputSettings = function () {
    let CARD_NUMBER_SEGMENT_SIZE = 4;
    document.addEventListener("DOMContentLoaded", ready);

    function ready() {
        applySettings();
    }

    function applySettings() {
        applySettingsToCardNumberComponents();
    }

    function applySettingsToCardNumberComponents() {
        let atmCardNumberComponents = document.body.querySelectorAll('.atm-card-number');
        for(let i = 0; i < atmCardNumberComponents.length; i++) {
            let inputElement = atmCardNumberComponents[i];
            inputElement.addEventListener('textChanged', function (e) {
                if(isSeparatorNeeded(inputElement.value.length, inputElement.maxLength)) {
                    inputElement.value += '-';
                }
            });
        }
    }

    function isSeparatorNeeded(currentLength, maxLength) {
        if(maxLength !== -1 && currentLength >= maxLength) {
            return false;
        }
        let countOfSegments = Math.floor(currentLength/CARD_NUMBER_SEGMENT_SIZE);
        let countOfSeparators = currentLength%CARD_NUMBER_SEGMENT_SIZE;
        return countOfSeparators + 1 === countOfSegments;
    }
};

applyAtmInputSettings();