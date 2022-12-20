#ifdef GL_ES
precision highp float;
#endif

uniform vec2 u_pos;
uniform vec2 u_texSize;
uniform float u_scale;
uniform sampler2D u_texture;
varying vec4 v_color;

void main()
{
    //    vec2 px = vec2(gl_FragCoord.x - u_pos.x, gl_FragCoord.y);
    //    vec2 uv = floor(px / u_scale) + 0.5;
    //    uv += 1.0 - clamp((1.0 - fract(px / u_scale)) * u_scale, 0.0, 1.0);
    //    uv.y = u_texSize.y - uv.y;
    // subpixel aa algorithm
    vec2 pixel = vec2(gl_FragCoord.x - u_pos.x, gl_FragCoord.y) / u_scale;
    vec2 aa = floor(pixel) + clamp(fract(pixel) * u_scale, 0.0, 1.0) - 0.5;
    aa.y = u_texSize.y - aa.y;
    gl_FragColor = v_color * texture2D(u_texture, aa / u_texSize);
}