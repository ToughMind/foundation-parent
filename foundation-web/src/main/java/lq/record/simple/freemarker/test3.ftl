迭代输出List中有Map：
<#list mapListKey as map>
<#list map?keys as key>
${key}和${map[key].id}和${map[key].name}
</#list>
</#list>

迭代输出Map中有List：
<#list listMapKey?keys as key>
${key}所对应的list:
<#list listMapKey[key] as u>
${u.id}和${u.name}
</#list>
</#list>