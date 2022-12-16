#ifdef GL_ES
precision highp float;
#endif

uniform vec2 u_pos;
uniform vec3 u_texSize;
uniform sampler2D u_texture;
varying vec4 v_color;
varying vec2 v_texCoords;

void main()
{
    vec2 px = vec2(gl_FragCoord.x - u_pos.x, gl_FragCoord.y);
    vec2 uv = floor(px / u_texSize.z) + 0.5;
    uv += 1.0 - clamp((1.0 - fract(px / u_texSize.z)) * u_texSize.z, 0.0, 1.0);

    uv.y = u_texSize.y - uv.y;

    //    vec2 px = v_texCoords * u_texSize.xy;
    //    vec2 uv = floor(px) + 0.5;
    //    uv += 1.0 - clamp((1.0 - fract(uv)) * u_texSize.z, 0.0, 1.0);




    gl_FragColor = v_color * texture2D(u_texture, uv / u_texSize.xy);
}