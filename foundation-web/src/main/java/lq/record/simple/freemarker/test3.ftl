�������List����Map��
<#list mapListKey as map>
<#list map?keys as key>
${key}��${map[key].id}��${map[key].name}
</#list>
</#list>

�������Map����List��
<#list listMapKey?keys as key>
${key}����Ӧ��list:
<#list listMapKey[key] as u>
${u.id}��${u.name}
</#list>
</#list>