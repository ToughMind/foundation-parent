 
${intKey/2}
${2+1}
${5-1}
${6/2}
${6/4}
 
${arrayKey[0]}
${arrayKey[1]}
${arrayKey[2]}
${arrayKey[3]}

${userArrayKey[0].id}��${userArrayKey[0].name}
${userArrayKey[1].id}��${userArrayKey[1].name}
����������飺
<#list userArrayKey as u>
${u.id}��${u.name}
</#list>

�������List��
<#list userListKey as u>
${u.id}��${u.name}
</#list>

�������Set��
<#list userSetKey as u>
${u.id}��${u.name}
</#list>

�������Map��
<#list userMapKey?keys as key>
${key}��${userMapKey[key].id}��${userMapKey[key].name}
</#list>

