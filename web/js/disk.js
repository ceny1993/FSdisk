appendByParentId(-1);


function appendByParentId(parentId) {
    var fileList = getFileList(parentId);
    for(var i=0;i<fileList.length;i++){
        var file = fileList[i];
        console.log(file);
        var str = '<div id="'+file["id"]+'" style="padding: 10px">';
        if(file["isFolder"]){
            str = str + '<a class="btn btn-info" onclick="appendByParentId('+file["id"]+')">'+file["fileName"]+'</a>';
            str = str + '<a style="float:right;margin-right: 50px" class="btn btn-danger" role="button" onclick="fileDelete('+file["id"]+')">删除</a>';
            str = str + '<label style="float:right;margin-right: 570px">'+getLocalTime(file["uploadTime"])+'</label>';
        }
        else{
            str = str + '<a class="btn btn-default">'+file["fileName"]+'</a>';
            str = str + '<a style="float:right;margin-right: 50px" class="btn btn-danger" role="button" onclick="fileDelete('+file["id"]+')">删除</a>';
            str = str + '<a style="float:right;margin-right: 200px" class="btn btn-default" role="button" onclick="fileDownload('+file["id"]+')">下载</a>';
            str = str + '<a style="float:right;margin-right: 20px" class="btn btn-default" role="button" onclick="fileShare('+file["id"]+')">分享</a>';
            str = str + '<label style="float:right;margin-right: 240px">'+getLocalTime(file["uploadTime"])+'</label>';
        }

        str = str + '</div>'
        //var str = "<p>"+file["fileName"]+"<\p>";
        $('#'+parentId).append(str);
    }
    if(parentId!=-1){
        console.log(parentId);
        //alert($('#'+parentId+">:first").attr("onclick"));
        $('#'+parentId+">:first").attr("onclick","remove("+parentId+")");
    }
}
function getFileList(parentId) {
    var result = {};
    $.ajax({
        url:"/user/file",
        type:"get",
        async:false,
        dataType:"json",
        data:{
            parentId:parentId
        },
        success:function (data) {
            result = data;
        }
    });
    return result;
}

function remove(paretId) {
    $('#'+paretId+'>div').fadeToggle();
}

window.funcList = {
    test:function () {
        alert("hi !test");
    }
};

function fileDownload(fileId) {
    alert(fileId);
}
function fileShare(fileId) {
    alert(fileId);
}
function fileDelete(fileId) {
    alert(fileId);
}

function getLocalTime(nS) {
    return new Date(parseInt(nS)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
}

//fuck css+js+html
