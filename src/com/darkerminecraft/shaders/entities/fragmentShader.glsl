#version 400 core

in vec2 pass_textureCoords;

out vec4 out_Color;

uniform sampler2D modelTexture;

void main() {

    out_Color = texture(modelTexture, pass_textureCoords);

} 