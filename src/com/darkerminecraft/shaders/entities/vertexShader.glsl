#version 400 core

in vec3 position;
in vec2 textureCoords;
in vec3 normal;

out vec2 pass_textureCoords;
out vec3 surfaceNormal;
out vec3 toLightVector;
out vec3 toCameraVector;

uniform mat4 transformationMatrix;
uniform mat4 projViewMatrix;

uniform vec3 cameraPosition;
uniform vec3 lightPosition;

void main() {

	vec4 worldPosition = transformationMatrix * vec4(position, 1.0);
    gl_Position = projViewMatrix * worldPosition;
	
	surfaceNormal = (transformationMatrix * vec4(normal, 0.0)).xyz;
	toLightVector = lightPosition - worldPosition.xyz;
	toCameraVector = cameraPosition - worldPosition.xyz; 
	
    pass_textureCoords = textureCoords;
    
}