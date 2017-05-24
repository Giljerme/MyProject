/**
 * Created by Ivan on 11.04.2017.
 */

const ITEM_PER_PAGE = 5;
let PAGE_COUNT = 1;
let ACTIVE_PAGE = 0;
let SORTING_DIRECTION = 1;
let ACTIVE_SORTING_COLUMN = 0;
let SORTING_COLUMN = $('.user-header').eq(3).html().split(' ')[0];;

$(document).ready(function() {
    let headers = $('.user-header');
    for(let index = 1; index < headers.length; index++) {
        let value = headers.eq(index).html();
        headers.eq(index).append('<span class="glyphicon glyphicon-triangle-top" aria-hidden="true"></span>');
        headers.eq(index).append('<span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span>');
        $('.glyphicon').hide();
        headers.eq(index).click(function () {
            $('.user-header span').hide();
            if (ACTIVE_SORTING_COLUMN == index) {
                SORTING_DIRECTION = (SORTING_DIRECTION + 1) % 2;
            } else {
                ACTIVE_SORTING_COLUMN = index;
                SORTING_DIRECTION = 1;
            }
            SORTING_COLUMN = value.split(' ')[0];
            $('.user-header span').eq((index - 1) * 2 + SORTING_DIRECTION).show();
            findUsersWithAllProperties()
        })
    }

    $.ajax({
        url: '../user/user-count',
        success: function (count) {
            if (count <= ITEM_PER_PAGE) {
                findUsersWithAllProperties();
                $('.pagination').hide();
            } else {
                PAGE_COUNT = Math.ceil(count / ITEM_PER_PAGE);
                for (let i = 0; i < PAGE_COUNT; i++) {
                    $('.pagination').append(`<li><a onclick="getPage('${i}')">${i + 1}</a></li>`);
                }

                $('.pagination li').eq(ACTIVE_PAGE).addClass("active");
                findUsersWithAllProperties();
            }
        }
    })
});

function findUsersWithAllProperties() {
    let user = $('#hello').html().split(' ')[1];
    let request = `../user/all?&page=${ACTIVE_PAGE}&columnForSorting=${SORTING_COLUMN}&direction=${SORTING_DIRECTION}`;

    $.ajax({
        url: request,
        success: function (data) {
            let row = '';

            $.each(data, function (i, item) {

                row +=
                    `<tr>
                        <td>${ACTIVE_PAGE * ITEM_PER_PAGE + i + 1}</td>
                        <td>${data[i].username}</td>
                        <td>${data[i].voices}</td>
                        <td>${round(data[i].rates)}</td>
                    </tr>`;

            });

            $('#users').html(row);
        },
        error: function () {
            console.log("We have a promblem1))");
        }
    })
}
