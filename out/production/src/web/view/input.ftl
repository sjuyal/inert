<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=1024" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <title>Inert - Multi browser instant testing</title>

    <meta name="description" content="Mid project evaluation presentation of Cloud computing project." />
    <meta name="author" content="Arpit Bhayani" />

    <link href="css/impress-demo.css" rel="stylesheet" />
	<link href="css/inert_input.css" rel="stylesheet" />
	<link href="css/dropzone.css" rel="stylesheet" />

    <link rel="shortcut icon" href="icon.png" />
    <link rel="apple-touch-icon" href="apple-touch-icon.png" />



</head>


<body class="impress-not-supported" style="background-color:#FFFFFF">

    <header>
        <div id="menu">
            <nav>
                <ul class="app_name">
                    <li><img class="app_image" src="icon.png" /></li>
                    <li><a href="/input">Inert</a></li>
                </ul>
            </nav>
        </div>
    </header>
    <div id="impress">

        <div class="step" data-x="0" data-y="0" data-z="0" data-scale="1">
            <q style="font-size:90%">
                <button id="create" class="nephritis-flat-button">Create New</button>
            </q>
        </q>
        </div>
	
		<div class="step" data-x="2000" data-y="0" data-z="0" data-scale="1">
			<form action="#" class="dropzone" id="my-awesome-dropzone">
				<input type="file" class="custom-file-input" name="report-file.html" size="100"/>
			</form>
			<button id="saveFile" class="nephritis-flat-button">Next</button>
		</div>
	
        <div class="step" data-x="4000" data-y="0" data-z="0" data-scale="1">
            <q style="font-size:90%">
                <div class="dropzone">

                    <!--div class="checkbox">
                        <input type="checkbox" id="gc" name="gc"/>
                        <label for="checkbox"></label>
                    </div>

                    <div class="checkbox">
                        <input type="checkbox" id="ff" name="ff"/>
                        <label for="checkbox"></label>
                    </div>

                    <div class="checkbox">
                        <input type="checkbox" id="ie" name="ie"/>
                        <label for="checkbox"></label>
                    </div-->

                    <div class="my">
                        <div class="checkbox">
                           <input type="checkbox" value="None" id="gc" name="gc"/>
                           <label for="gc"></label>
                        </div>
                        <div class="normtext">Google Chrome</div>
                    </div>
                    <br/>
                    <div class="my">
                        <div class="checkbox">
                           <input type="checkbox" value="None" id="ff" name="ff"/>
                           <label for="ff"></label>
                        </div>
                        <div class="normtext">Firefox</div>
                    </div>
                    <br/>
                    <div class="my">
                        <div class="checkbox">
                           <input type="checkbox" value="None" id="ie" name="ie"/>
                           <label for="ie"></label>
                        </div>
                        <div class="normtext">Internet Explorer</div>
                    </div>

                    <!--input type="checkbox" id="gc" name="gc">Google Chrome</input>
                    <input type="checkbox" id="ff" name="ff">Firefox</input>
                    <input type="checkbox" id="ie" name="ie">Internet Explorer</input>
                    <div class="checkbox" id="gc"></div>
                    <div class="checkbox" id="ff"></div>
                    <div class="checkbox" id="ie"></div-->

                </div>
                <button id="saveBrowsers" class="nephritis-flat-button">Next</button>
            </q>
        </div>

        <div class="step" data-x="6000" data-y="0" data-z="0" data-scale="1">
            <q style="font-size:90%">
                <button id="exec" class="nephritis-flat-button">Execute</button>
            </q>
            <textarea readonly id="output_area"></textarea>
            
        </q>
        </div>
    </div>

    <div class="fallback-message">
        <p>Your browser <b>doesn't support the features required</b> by impress.js, so you are presented with a simplified version of this presentation.</p>
        <p>For the best experience please use the latest <b>Chrome</b>, <b>Safari</b> or <b>Firefox</b> browser.</p>
    </div>


    <div class="hint">
        <p>Use a spacebar or arrow keys to navigate</p>
    </div>
    <script>
        if ("ontouchstart" in document.documentElement) {
            document.querySelector(".hint").innerHTML = "<p>Tap on the left or right to navigate</p>";
        }
    </script>


    <script src="js/impress.js"></script>
	<script src="js/jquery.min.js"></script>
	<script src="js/dropzone.js"></script>
	<script src="js/fileupload.js"></script>
    <script>
        impress().init();

$('#saveFile').on('click', function(e) {
    impress().next();
    e.stopPropagation();
});

$('#saveBrowsers').on('click', function(e) {
    $.post(
        "/saveBrowsers", {
            gc : $('input:checkbox[name=gc]').is(':checked'),
            ff : $('input:checkbox[name=ff]').is(':checked'),
            ie : $('input:checkbox[name=ie]').is(':checked')
        }
    );

    impress().next();
    e.stopPropagation();
});

$('#create').on('click', function(e) {

    $.post(
        "/createId", {}
    );

    impress().next();
    e.stopPropagation();
});

var timer, delay = 1; //5 minutes counted in milliseconds.

$('#exec').on('click', function(e) {

    $('#output_area').html("Testing your file on server ...\n");

    $.post(
        "/exec", {}
    );

    timer = setInterval(function(){
        $.ajax({
          type: 'GET',
          url: '/getOutput',
          success: function(response){
            if( response == "null" ) {

            }
            else if( response == "done" ) {
                $('#output_area').append(response);
                clearInterval(timer);
            }
            else {
                $('#output_area').append(response);
            }
          },
          error: function(xhr, textStatus, errorThrown){

          }
        });
    }, delay);

    e.stopPropagation();

});

</script>

</body>

</html>