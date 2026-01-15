$files = Get-ChildItem "c:\Users\rober\OneDrive\Documentos\Cursos\Taller de Desarollo\MsCaja\src\main\java\com\pago\service\impl\*.java"
foreach ($file in $files) {
    $content = Get-Content $file.FullName -Raw
    $newContent = $content -replace "package com.pago.service.Imp;", "package com.pago.service.impl;"
    Set-Content -Path $file.FullName -Value $newContent
    Write-Host "Updated $($file.Name)"
}
