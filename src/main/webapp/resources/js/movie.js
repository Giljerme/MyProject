$(document).ready(function() {
    findAllMovies();
    findAllUsers();
});

function findAllMovies() {
    let user = $('#hello').html().split(' ')[1];
    let request = `movie/top-movie?name=${user}`;
    let userRates = [];

    $.ajax({
        url: request,
        success: function (data) {
            let row = '';

            $.each(data, function (i, item) {
                row +=
                    `<tr>
                        <td>${i + 1}</td>
                        <td>${data[i].name}</td>
                        <td>${data[i].year}</td>
                        <td>${data[i].voiceCount}</td>
                        <td>${round(data[i].rate)}</td>
                        <td>${setListenerForChangeRate(data[i].id, user)}</td>
                    </tr>`;

                userRates.push(data[i].yourRate);
            });

            $('#movies').html(row);
            setUsersRate(userRates);
        }
    })
};

function findAllUsers() {
    let request = 'user/top-users';

    $.ajax({
        url: request,
        success: function (data) {
            let row = '';

            $.each(data, function (i, item) {
                row +=
                    `<tr>
                        <td>${i + 1}</td>
                        <td>${data[i].username}</td>
                        <td>${data[i].voices}</td>
                        <td>${round(data[i].rates)}</td>
                    </tr>`;

            });

            $('#users').html(row);
        }
    })
};

function sendMark(movie, mark, user, style) {
    const path = `rate/add?userName=${user}&idMovie=${movie}&rate=${mark}`;
    $('.movie-choose').eq(movie - 1).html(mark + DROP_UP_COMPONENT);

    $.ajax({
        url: path,
        type: 'POST',
        success: function (status) {
            findAllMovies();
            findAllUsers();
        }
    })
}

