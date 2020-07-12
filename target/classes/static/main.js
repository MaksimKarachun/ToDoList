$(document).ready (function() {

    //update form
    const appendDate = function(data){
        $('#current-date').html('<div class="date-text">' + data + '</div>');
    };

    //get date and show form
    $('#show-form').click(function() {
        $.ajax({
           cache: false,
           url: '/date',
           method: 'GET',
           success: function(data){
           appendDate(data);
           }
        });

        $('#date-form').css('display', 'flex');
    });

    //if click not on form
    $('#date-form').click(function(event) {
        if ( event.target === this) {
            $(this).css('display', 'none');
        }
    });

});