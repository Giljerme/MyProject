const ITEM_PER_PAGE = 5;
let PAGE_COUNT = 1;
let ACTIVE_PAGE = 0;
let SORTING_DIRECTION = 0;
let ACTIVE_SORTING_COLUMN = 0;
let SORTING_COLUMN = $('.movie-header').eq(1).html();

$(document).ready(function() {
    let headers = $('.movie-header');
    for(let index = 1; index < headers.length; index++) {
        let value = headers.eq(index).html();
        headers.eq(index).append('<span class="glyphicon glyphicon-triangle-top" aria-hidden="true"></span>');
        headers.eq(index).append('<span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span>');
        $('.glyphicon').hide();
        headers.eq(index).click(function () {
            $('.movie-header span').hide();
            if (ACTIVE_SORTING_COLUMN == index) {
                SORTING_DIRECTION = (SORTING_DIRECTION + 1) % 2;
            } else {
                ACTIVE_SORTING_COLUMN = index;
                SORTING_DIRECTION = 1;
            }
            SORTING_COLUMN = value.split(' ')[0];
            $('.movie-header span').eq((index - 1) * 2 + SORTING_DIRECTION).show();
            findAllMoviesWithAllProperties()
        })
    };

    $.ajax({
        url: '../movie/movie-count',
        success: function (count) {
            if (count <= ITEM_PER_PAGE) {
                findAllMoviesWithAllProperties();
                $('.pagination').hide();
            } else {
                PAGE_COUNT = Math.ceil(count / ITEM_PER_PAGE);
                for (let i = 0; i < PAGE_COUNT; i++) {
                    $('.pagination').append(`<li><a onclick="getPage('${i}')">${i + 1}</a></li>`);
                }

                $('.pagination li').eq(ACTIVE_PAGE).addClass("active");
                findAllMoviesWithAllProperties();
            }
        }
    })
});

function findAllMoviesWithAllProperties() {
    let user = $('#hello').html().split(' ')[1];
    let request = `../movie/all?name=${user}&page=${ACTIVE_PAGE}&columnForSorting=${SORTING_COLUMN}&direction=${SORTING_DIRECTION}`;
    let userRates = [];

    $.ajax({
        url: request,
        success: function (data) {
            let row = '';

            $.each(data, function (i, item) {

                row +=
                    `<tr>
                        <td>${ACTIVE_PAGE * ITEM_PER_PAGE + i + 1}</td>
                        <td>${data[i].name}</td>
                        <td>${data[i].year}</td>
                        <td>${data[i].director}</td>
                        <td>${data[i].budget}</td>
                        <td>${data[i].boxOffice}</td>
                        <td>${data[i].description}</td>
                        <td>${data[i].voiceCount}</td>
                        <td>${round(data[i].rate)}</td>
                        <td>${setListenerForChangeRate(data[i].id, user)}</td>
                    </tr>`;

                userRates.push(data[i].yourRate);

            });

            $('#movies').html(row);
            setUsersRate(userRates);
        },
        error: function () {
            console.log("We have a promblem1))");
        }
    })
}

function getPage(number) {
    ACTIVE_PAGE = number;
    findAllMoviesWithAllProperties();

    $('.active').removeClass("active");
    $('.pagination li').eq(number).addClass("active");

}

function sendMark(movie, mark, user, style) {
    const path = `../rate/add?userName=${user}&idMovie=${movie}&rate=${mark}`;
    $('.movie-choose').eq(movie - 1).html(mark + DROP_UP_COMPONENT);

    $.ajax({
        url: path,
        type: 'POST',
        success: function (status) {
            findAllMoviesWithAllProperties();
        }
    })
}
