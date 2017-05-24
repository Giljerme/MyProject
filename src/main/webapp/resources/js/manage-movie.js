const ITEM_PER_PAGE = 5;
let PAGE_COUNT = 1;
let ACTIVE_PAGE = 0;

$(document).ready(function() {
    $('#add-movie').click(function() {
        $('#edit-movie-form').toggle();
        $('#movie-id').html('');
        $('#movie-name').val('');
        $('#movie-year').val('');
        $('#movie-box').val('');
        $('#movie-director').val('');
        $('#movie-desc').val('');
        $('#movie-budget').val('');
    });
    $('#movie-id').html(0)
    $('#confirm-booking').click(function () {

        let object = buildObject();
        console.log(object);

        $.ajax({
            type: 'POST',
            url: 'movie/add' + object,
            success: function () {
                $('#movie-id').html('');
                $('#movie-name').val('');
                $('#movie-year').val('');
                $('#movie-box').val('');
                $('#movie-director').val('');
                $('#movie-desc').val('');
                $('#movie-budget').val('');
            }
        });
    });

    $.ajax({
        url: 'movie/movie-count',
        success: function (count) {
            console.log(count);
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
        }, error: function() {
            console.log("!");
        }
    });
});

function findAllMoviesWithAllProperties() {
    let user = $('#hello').html().split(' ')[1];
    let request = `movie/all?name=${user}&page=${ACTIVE_PAGE}&columnForSorting=Name&direction=1`;

    $.ajax({
        url: request,
        success: function (data) {
            let row = '';

            $.each(data, function (i, item) {
                let editFunc = `editSomeMovie(${data[i].id}, '${data[i].name}', ${data[i].year}, 
                                '${data[i].director}', ${data[i].budget}, ${data[i].boxOffice}, '${data[i].description}')`;
                let deleteFunc = `deleteSomeMovie(${data[i].id})`;

                row +=
                    `<tr>
                        <td>${ACTIVE_PAGE * ITEM_PER_PAGE + i + 1}</td>
                        <td>${data[i].name}</td>
                        <td>${data[i].year}</td>
                        <td>${data[i].director}</td>
                        <td>${data[i].budget}</td>
                        <td>${data[i].boxOffice}</td>
                        <td>${data[i].description}</td>
                        <td onclick="${editFunc}"><span class="glyphicon glyphicon glyphicon-edit" aria-hidden="true"></span></td>
                        <td onclick="${deleteFunc}">Delete</a></td>
                    </tr>`;

            });

            $('#movies').html(row);
        }
    })
}

function editSomeMovie(id, name, yearOfRelease, director, budget, boxOffice, description) {
    $('#movie-id').html(id);
    $('#movie-name').val(name);
    $('#movie-year').val(yearOfRelease);
    $('#movie-box').val(boxOffice);
    $('#movie-director').val(director);
    $('#movie-desc').val(description);
    $('#movie-budget').val(budget);

    $('#edit-movie-form').toggle();
}

function deleteSomeMovie(id) {
    let request = `movie/delete?id=${id}`;

    $.ajax({
        url: request,
        type: 'DELETE',
        data: id,
        success: function () {
            console.log("Done!")
        }
    })
}

function buildObject() {
    let data = [];

    data.id = $('#movie-id').html();
    data.name = $('#movie-name').val();
    data.yearOfRelease = $('#movie-year').val();
    data.boxOffice = $('#movie-box').val();
    data.director = $('#movie-director').val();
    data.description = $('#movie-desc').val();
    data.budget = $('#movie-budget').val();
    /*let data = `{
    "id" : ${$('#movie-id').html()},
    "name" : "${$('#movie-name').val()}",
    "yearOfRelease" : ${$('#movie-year').val()},
    "boxOffice" : ${$('#movie-box').val()},
    "director" : "${$('#movie-director').val()}",
    "description" : "${$('#movie-desc').val()}",
    "budget" : ${$('#movie-budget').val()}
    }`;*/

    return `?id=${data.id ? data.id : 0}&name=${data.name}&budget=${data.budget}&box=${data.boxOffice}&desc=${data.description}&year=${data.yearOfRelease}&director=${data.director}`;
}


function getPage(number) {
    ACTIVE_PAGE = number;
    findAllMoviesWithAllProperties();

    $('.active').removeClass("active");
    $('.pagination li').eq(number).addClass("active");

}
