 
${intKey/2}
${2+1}
${5-1}
${6/2}
${6/4}
 
${arrayKey[0]}
${arrayKey[1]}
${arrayKey[2]}
${arrayKey[3]}

${userArrayKey[0].id}和${userArrayKey[0].name}
${userArrayKey[1].id}和${userArrayKey[1].name}
迭代输出数组：
<#list userArrayKey as u>
${u.id}和${u.name}
</#list>

迭代输出List：
<#list userListKey as u>
${u.id}和${u.name}
</#list>

迭代输出Set：
<#list userSetKey as u>
${u.id}和${u.name}
</#list>

迭代输出Map：
<#list userMapKey?keys as key>
${key}和${userMapKey[key].id}和${userMapKey[key].name}
</#list>

