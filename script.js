const username = sessionStorage.getItem("username");
const fname = sessionStorage.getItem("fname");
const lname = sessionStorage.getItem("fname");
const role = sessionStorage.getItem("fname");
//document.getElementById("name").innerHTML = username;

getDefects();
getProjects();
if(role === "manager"){
    getTesters();
}

async function getDefects(){
    let httpResponse = await fetch("https://bugcatcher.coe.revaturelabs.com/5/defects");

    if (httpResponse.status !== 200){
        console.log("getDefects Failed");
        return;
    }

    let responseBody = await httpResponse.json();
    
    for (let i = 0; i < responseBody.length; i++){
        let defectObj = responseBody[i];
        if(defectObj.assignedTo === username || role === "manager"){
            createRow(defectObj);   
        }
    }
}

async function getTesters(){
    let httpResponse = await fetch("https://bugcatcher.coe.revaturelabs.com/5/employees");

    if (httpResponse.status !== 200){
        console.log("getEmployees Failed");
        return;
    }

    let responseBody = await httpResponse.json();
    
    for (let i = 0; i < responseBody.length; i++){
        let employeeObj = responseBody[i];
        if(employeeObj.role === "Tester"){
            document.getElementById("selectTester").innerHTML += `<option value="${employeeObj.username}">${employeeObj.fname} ${employeeObj.lname}</option>`;  
        }
    }
}

async function getProjects(){
    const projectList = document.getElementById("projectList");
    
    const projectSelector = document.getElementById("projectSelector");


    let httpResponse = await fetch("https://bugcatcher.coe.revaturelabs.com/5/projects");

    if (httpResponse.status !== 200){
        console.log("getProjects Failed");
        return;
    }

    let responseBody = await httpResponse.json();

    projectSelector.innerHTML = "";
    projectList.innerHTML = "";
    for (let i = 0; i < responseBody.length; i++){
        let projectObj = responseBody[i];
        const node = document.createElement("li");
       
        node.innerHTML = `<details open>
                            <summary>${projectObj.title}</summary>
                            <ul>
                                <li>Id: ${projectObj.projectId}</li>
                                <li>About: ${projectObj.about}</li>
                                <li><details open>
                                    <summary>Test Summary Reports</summary>
                                    <ul id="project${projectObj.projectId}Summaries"></ul>
                                </details></li>

                            </ul>
                        </details>`;
        projectList.appendChild(node);
        projectSelector.innerHTML += `<option value="${projectObj.projectId}">${projectObj.title}</option>`;
        getSummaries(projectObj.projectId);
    }

}

async function getSummaries(projectId){
    const summaryList = document.getElementById(`project${projectId}Summaries`);
   
    const summarySelector = document.getElementById("summarySelector");
    

    let httpResponse = await fetch("https://bugcatcher.coe.revaturelabs.com/5/summaries");

    if (httpResponse.status !== 200){
        console.log("getSummaries Failed");
        return;
    }

    let responseBody = await httpResponse.json();

    summaryList.innerHTML = "";
    summarySelector.innerHTML = "";
    
    for (let i = 0; i < responseBody.length; i++){
        let summaryObj = responseBody[i];
        const node = document.createElement("li");
        summarySelector.innerHTML += `<option value="${summaryObj.summaryId}">${summaryObj.reasonForTesting}</option>`;
        
        if (summaryObj.projectId === projectId){
            
            node.innerHTML = `<details open>
                                <summary>${summaryObj.reasonForTesting}</summary>
                                <ul>
                                    <li>Summary Id: ${summaryObj.summaryId}</li>
                                    <li><details open>
                                        <summary>Test Cases</summary>
                                        <ul id="summary${summaryObj.summaryId}Cases"></ul>
                                    </details></li>
                                </ul>
                            </details>`;
                      
            summaryList.appendChild(node);
            
            getCases(summaryObj.summaryId);          
        }
    }
    
}

async function getCases(summaryId){
    const caseList = document.getElementById(`summary${summaryId}Cases`);
    
    let httpResponse = await fetch("https://bugcatcher.coe.revaturelabs.com/5/cases");

    if (httpResponse.status !== 200){
        console.log("getCases Failed");
        return;
    }

    let responseBody = await httpResponse.json();

    caseList.innerHTML = "";
    for (let i = 0; i < responseBody.length; i++){
        let caseObj = responseBody[i];
        const node = document.createElement("li");
        node.setAttribute("id", `li${caseObj.caseId}`);
        
        if (caseObj.summaryId === summaryId && (caseObj.testedBy === username || caseObj.testedBy === null || username === "manager")){
            
            node.innerHTML = `<details open>
                                <summary>${caseObj.featureTested}</summary>
                                <ul>
                                    <li>Case Id: ${caseObj.caseId}</li>
                                    <li>Tested By: ${caseObj.testedBy}</li>
                                    <li>Result: ${caseObj.result}</li>
                                    <li><button id="delete${caseObj.caseId}" onclick="deleteCase(${caseObj.caseId})">Delete</button></li>
                                </ul>
                            </details>`;
            caseList.appendChild(node);       
        }
    }
}

async function updateDefect(defectId){
    let selectorId = "selector" + defectId;
    let selectorValue = document.getElementById(selectorId).value;
    let updateInfo = {
        status: selectorValue
    }

    let updateJSON = JSON.stringify(updateInfo);

    let config = {
        method: "PATCH", 
        headers: {'Content-Type':"application/json"},
        body: updateJSON
    }

    let httpResponse = await fetch(`https://bugcatcher.coe.revaturelabs.com/5/defects/${defectId}`, config);

    if (httpResponse.status === 204){
        updateRow(defectId, selectorValue);
    }
}

function createRow(defectObj){
    const defectTable = document.getElementById("defectTable");
    const node = document.createElement("tr");
   
    node.setAttribute("id", `row${defectObj.defectId}`);
    node.setAttribute("class", "defectRow");

    if (role === "manager"){
        node.innerHTML =   `<td>${defectObj.defectId}</td>
                            <td>${defectObj.desc}</td>
                            <td>${defectObj.assignedTo}</td>
                            <td id="statusTD${defectObj.defectId}">${defectObj.status}</td>
                            <td><select id="selector${defectObj.defectId}">
                                <option value="Pending">Pending</option>
                                <option value="Accepted">Accepted</option>
                                <option value="Declined">Declined</option>
                                <option value="Rejected">Rejected</option>
                                <option value="Fixed">Fixed</option>
                                <option value="Shelved">Shelved</option>
                            </select></td>
                            <td><button id="update${defectObj.defectId}" onclick="updateDefect(${defectObj.defectId})">Update</button></td>`;
    }else if (defectObj.status === "Pending"){
        node.innerHTML =   `<td>${defectObj.defectId}</td>
                            <td>${defectObj.desc}</td>
                            <td id="statusTD${defectObj.defectId}">${defectObj.status}</td>
                            <td><select id="selector${defectObj.defectId}">
                                <option value="Accepted">Accepted</option>
                                <option value="Declined">Declined</option>
                            </select></td>
                            <td><button id="update${defectObj.defectId}" onclick="updateDefect(${defectObj.defectId})">Update</button></td>`;
    } else if (defectObj.status === "Accepted"){
        node.innerHTML =   `<td>${defectObj.defectId}</td>
                            <td>${defectObj.desc}</td>
                            <td id="statusTD${defectObj.defectId}">${defectObj.status}</td>
                            <td><select id="selector${defectObj.defectId}">
                                <option value="Rejected">Rejected</option>
                                <option value="Fixed">Fixed</option>
                                <option value="Shelved">Shelved</option>
                            </select></td>
                            <td><button id="update${defectObj.defectId}" onclick="updateDefect(${defectObj.defectId})">Update</button></td>`;
       

    } else {
        node.innerHTML =   `<td>${defectObj.defectId}</td>
                            <td>${defectObj.desc}</td>
                            <td id="statusTD${defectObj.defectId}">${defectObj.status}</td>
                            <td></td>
                            <td></td>`;
    } 
    defectTable.appendChild(node);
}

function updateRow(defectId, selectorValue){
    
    
    if (role === "manager"){        
        document.getElementById(`statusTD${defectId}`).innerHTML = `${selectorValue}`;
        document.getElementById(`selector${defectId}`).innerHTML = `<option value="Pending">Pending</option>
                                                                    <option value="Accepted">Accepted</option>
                                                                    <option value="Declined">Declined</option>
                                                                    <option value="Rejected">Rejected</option>
                                                                    <option value="Fixed">Fixed</option>
                                                                    <option value="Shelved">Shelved</option>`;

    } else if (selectorValue === "Accepted"){
        document.getElementById(`statusTD${defectId}`).innerHTML = `${selectorValue}`;
        document.getElementById(`selector${defectId}`).innerHTML = `<option value="Rejected">Rejected</option>
                                                                    <option value="Fixed">Fixed</option>
                                                                    <option value="Shelved">Shelved</option>`;
    } else {
        
        document.getElementById(`statusTD${defectId}`).innerHTML = `${selectorValue}`;
        document.getElementById(`selector${defectId}`).remove();
        document.getElementById(`update${defectId}`).remove();
    }
    
}

async function createProject() {
    let projectInfo = {
        title: document.getElementById("projectTitle").value,
        about: document.getElementById("projectAbout").value
    }

    let projectJSON = JSON.stringify(projectInfo);

    let config = {
        method: "POST", 
        headers: {'Content-Type':"application/json"},
        body: projectJSON
    }

    let httpResponse = await fetch("https://bugcatcher.coe.revaturelabs.com/5/projects", config);

    if(httpResponse.status === 200){ 
        let responseBody = await httpResponse.json(); 
        alert("Project successfully posted!");
    } else {
        let responseBody = await httpResponse.json(); 
        alert(responseBody.detail);
    }

    getProjects();
}

async function createSummary() {
    let summaryInfo = {
        projectId: document.getElementById("projectSelector").value,
        startDate: 0,
        endDate: 0,
        featuresInScope: ["string"],
        testEnvironmentDesc: "string",
        objectives: ["Performance"],
        reasonForTesting: document.getElementById("summaryReason").value
    }

    let summaryJSON = JSON.stringify(summaryInfo);

    let config = {
        method: "POST", 
        headers: {'Content-Type':"application/json"},
        body: summaryJSON
    }

    let httpResponse = await fetch("https://bugcatcher.coe.revaturelabs.com/5/summaries", config);

    if(httpResponse.status === 201){ 
        let responseBody = await httpResponse.json(); 
        alert("Summary successfully posted!");
    } else {
        let responseBody = await httpResponse.json(); 
        alert(responseBody.detail);
    }

    getProjects();
}

async function createCase() {
    
    let caseInfo = {
        summaryId: document.getElementById("summarySelector").value,
        featureTested: document.getElementById("caseFeature").value,
        testedBy: username,
        desc: "string",
        isAutomated: true,
        result: document.getElementById("caseResultSelector").value,
        posOrNeg: "Positive",
        boxType: "Whitebox"
    }

    let caseJSON = JSON.stringify(caseInfo);

    let config = {
        method: "POST", 
        headers: {'Content-Type':"application/json"},
        body: caseJSON
    }

    let httpResponse = await fetch("https://bugcatcher.coe.revaturelabs.com/5/cases", config);

    if(httpResponse.status === 201){ 
        let responseBody = await httpResponse.json(); 
        alert("Case successfully posted!");
    } else {
        let responseBody = await httpResponse.json(); 
        alert(responseBody.detail);
    }

    getProjects();
}

async function deleteCase(caseId){

    let config = {
        method: "DELETE" 
    }
    
    let httpResponse = await fetch(`https://bugcatcher.coe.revaturelabs.com/5/cases/${caseId}`, config);

    if(httpResponse.status === 204){ 
        const element = document.getElementById(`li${caseId}`);
        element.remove();
        alert("Case successfully deleted!");
    } else {
        let responseBody = await httpResponse.json(); 
        alert(responseBody.detail);
    } 
}

async function Assign() {
    let defectInfo = {
        assignedTo: document.getElementById("selectTester").value,
        dateReported: 0,
        desc: document.getElementById("defectName").value,
        stepsToReproduce: "string",
        severity: "Low",
        priority: "Low"
    }

    let defectJSON = JSON.stringify(defectInfo);

    let config = {
        method: "POST", 
        headers: {'Content-Type':"application/json"},
        body: defectJSON
    }

    let httpResponse = await fetch("https://bugcatcher.coe.revaturelabs.com/5/defects", config);

    if(httpResponse.status === 201){ 
        let responseBody = await httpResponse.json(); 
        alert("Defect successfully posted!");
    } else {
        let responseBody = await httpResponse.json(); 
        alert(responseBody.detail);
    }
    
    let defectRows = document.getElementsByClassName("defectRow");
    while(defectRows[0]){
        defectRows[0].parentNode.removeChild(defectRows[0]);
    }
    getDefects();  
}