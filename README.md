Por fin terminado :')
Esta es la collection para probar: 
https://www.getpostman.com/collections/38bc8a7767107eb64fca


apidoc.json   => Es la definicion de los endpoints, codigos http y templates para mapear requests y responses

lambdas.yml   => Es una definicion SAM de las lambdas que se desplegaron

policies.json => Son todos los permisos y roles que se le dieron a las lambdas 

Nota: Se creó una tabla en DynamoDB llamada satellites-info para alamacenar los satelites a guardar

Para crear la lambda en go desde cero:


1. Actualizar o instalar golang

- Linux  o Mac

        git clone https://github.com/udhos/update-golang
        cd update-golang
        sudo ./update-golang.sh
  
- Windows
  
        https://chocolatey.org/packages/golang

2. Obtener dependenecias
   
        sudo go get -v all

3. Compilar
   
- Linux o Mac
  
        GOOS=linux go build -o ./build/main ./function/main.go

- Windows con powershell

        $Env:GOOS = "linux"; $Env:GOARCH = "amd64"; go build -o .\build\main .\function\main.go

4. Hacer un zip

- Linux o Mac

        zip -jrm ./build/main.zip ./build/main

- Windows con powershell

        Compress-Archive -Path .\build\main -DestinationPath .\build\main.zip
  
    - Darle permisos de ejecucion (Esto es teniendo en cuenta que se configuro WSL2 y la maquina virtual previamente)

             sudo chmod 777 build/main

5. configurar la consola 
   
- Configurar el handler con: main

