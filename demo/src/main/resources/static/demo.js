$(document).ready(function(){

refreshTable();

$('#add').on('click', (function() {
    create();
}));

$('#update').on('click', (function() {
    update();
}));

$('#get').on('click', (function() {
    getById();
}));

$('#get_all').on('click', (function() {
    refreshTable();
}));

$('#delete').on('click', (function() {
    del();
}));

function refreshTable() {
$.ajax({
    url: 'demo/api/employee/',
    method: 'GET',
    contentType: 'application/json',
    success: function(data) {
            $('table').html('<caption>Salary List</caption><thead><tr><th>ID</th><th>Name</th><th>Salary</th></tr>' +
                            '</thead><tbody></tbody>');
            for (var i = 0; i < data.length; i++) {
                $('tbody').append('<tr id = "row' + data[i].id + '"><td>' + data[i].id + '</td>' +
                '<td>' + data[i].name + '</td><td>' + data[i].salary + '</td></tr>');
            }
    }
});
};

function create() {
$.ajax({
    url: 'demo/api/employee/',
    method: 'POST',
    data: JSON.stringify(
    {
        "name": $('#name_add').val(),
        "salary": $('#salary_add').val()
    }),
    contentType: 'application/json',
    success: function(data) {
        refreshTable();
        $('#name_add').val('');
        $('#salary_add').val('');
    },
    error: function (jqXHR, textStatus, errorThrown) {
        if (jqXHR.status === 500 || jqXHR.status === 400) {
            throw new Error("Name must be more than 3 symbols length, salary must be positive " + errorThrown);
        }
    }
});
};

function update() {
$.ajax({
    url: 'demo/api/employee/' + 1,
    method: 'PUT',
    data: JSON.stringify(
    {
        "id": $('#id_update').val(),
        "name": $('#name_update').val(),
        "salary": $('#salary_update').val()
    }),
    contentType: 'application/json',
    success: function(data) {
        refreshTable();
        $('#id_update').val('');
        $('#name_update').val('');
        $('#salary_update').val('');
    },
    error: function (jqXHR, textStatus, errorThrown) {
        if (jqXHR.status === 500 || jqXHR.status === 400) {
            throw new Error("Name must be more than 3 symbols length, salary must be positive " + errorThrown);
        }
    }
});
};

function getById() {
$.ajax({
    url: 'demo/api/employee/' + $('#id_get').val(),
    method: 'GET',
    contentType: 'application/json',
    success: function(data) {
            if(data != null) {
            $('table').html('<caption>Salary List</caption><thead><tr><th>ID</th><th>Name</th><th>Salary</th></tr>' +
                            '</thead><tbody></tbody>');
            $('tbody').append('<tr id = "row' + data.id + '"><td>' + data.id + '</td>' +
                              '<td>' + data.name + '</td><td>' + data.salary + '</td></tr>');
            $('#id_get').val('');
            }
            else {
                throw new Error("Id is not found:" + errorThrown);
            }
    },
    error: function (jqXHR, textStatus, errorThrown) {
        if (jqXHR.status === 500) {
            throw new Error("Id must be positive number" + errorThrown);
        }
    }
});
};

function del() {
$.ajax({
    url: 'demo/api/employee/' + $('#id_del').val(),
    method: 'DELETE',
    contentType: 'application/json',
    success: function(data) {
            if(data != null) {
                refreshTable();
                $('#id_del').val('');
            }
            else {
                throw new Error("Id is not found:" + errorThrown);
            }
    }
});
};

});