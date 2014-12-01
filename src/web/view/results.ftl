<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" href="http://localhost/input/results.css" />
        <link rel="shortcut icon" href="http://localhost/input/icon.png" type="image/png"/>
        <title>CricAlign</title>
    </head>
    <body>
        <header>
            <div id="menu">
               <nav>
                <ul class="app_name">
                    <li><img class="app_image" src="http://localhost/input/icon.png"/></li>
                    <li><a href="#">CricAlign</a></li>
                </ul>
                <ul class="user_section">
                    <li><a href="#">What is CricAlign?</a></li>
                    <li>|</li>
                    <li><a href="#">Developers</a></li>
                </ul>
               </nav>
            </div>
        </header>

        <br/>

        <div class="content">
            <div class="report">
                <div class="title">Match Report</div>
                <div class="report-content">
                    <#list reportLines as reportLine>
                        ${reportLine}
                    </#list>
                </div>
            </div>
            <div class="commentary">
                <div class="title">Commentary</div>
                <div class="commentary-content">
                    <#list comm1 as c>
                        <p>${c.ballNumberString}</p>
                        <p>${c.text}</p>
                        <br/>
                    </#list>
                    <#list comm2 as c>
                        <p>${c.ballNumberString}</p>
                        <p>${c.text}</p>
                        <br/>
                    </#list>
                </div>
            </div>
        </div>


    </body>
</html>