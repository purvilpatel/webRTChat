(function($) {

    $(document).ready(function() {

        try {
            var recognition = new webkitSpeechRecognition();
        } catch (e) {
            var recognition = Object;
        }
        recognition.continuous = true;
        recognition.interimResults = true;

        var interimResult = '';
        var textArea = $('#speech-page-content');
        var textAreaID = 'speech-page-content';


        var space = true;
        $(function() {
            $(document).keydown(function(evt) {
                if (evt.keyCode == 32) {

                    if (space) {
                        startRecognition();
                        space = false;
                    } else {
                        recognition.stop();
                        space = true;
                    }
                }
            });
        });


        var startRecognition = function() {
            $('.speech-content-mic').removeClass('speech-mic').addClass('speech-mic-works');
            textArea.focus();
            recognition.start();
        };

        recognition.onresult = function(event) {
            var pos = textArea.getCursorPosition() - interimResult.length;
            textArea.val(textArea.val().replace(interimResult, ''));
            interimResult = '';
            textArea.setCursorPosition(pos);
            for (var i = event.resultIndex; i < event.results.length; ++i) {
                if (event.results[i].isFinal) {
                    // text area
                    //insertAtCaret(textAreaID, getTimeStamp() + " - "+ event.results[i][0].transcript+"\n");

                    var newContent = document.getElementById('newContent');
                    var userName = document.getElementById('usern').innerHTML;
                    var valueParameter = getTimeStamp() + " - " + userName 
                                        + " - " + event.results[i][0].transcript;
                    newContent.innerHTML = newContent.innerHTML  +"<div class=\"appendData\">" 
                                            + valueParameter + "<br/></div>"
                    //newContent.innerHTML = newContent.innerHTML  + valueParameter;
                    //insertAtCaret(textAreaID, );
                    AJAXFunction(valueParameter);

                    document.getElementById('speech-page-content').value = '';
                } else {
                    isFinished = false;
                    // text area
                    insertAtCaret(textAreaID, event.results[i][0].transcript + '\u200B');
                    interimResult += event.results[i][0].transcript + '\u200B';
                }
            }
        };

        var AJAXFunction = function(valueParameter) {
            var userId = document.getElementById('uid').innerHTML;
            $.get('ActionServlet', {
                    new_value: valueParameter, ids: userId
                },
                function(returnValueParameter) {
                    var newContent = document.getElementById('newContent');
                    newContent.innerHTML = returnValueParameter;
                });
        }
        var getTimeStamp = function() {
            var d = new Date();
            var curr_hour = d.getHours();
            var curr_min = d.getMinutes();
            var curr_sec = d.getSeconds();

            if (curr_hour < 10) {
                curr_hour = "0" + curr_hour;
            }
            if (curr_min < 10) {
                curr_min = "0" + curr_min;
            }
            if (curr_sec < 10) {
                curr_sec = "0" + curr_sec;
            }
            return curr_hour + ":" + curr_min + ":" + curr_sec;
        }

        recognition.onend = function() {
            $('.speech-content-mic').removeClass('speech-mic-works').addClass('speech-mic');
        };
    });
})(jQuery);