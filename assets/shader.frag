#ifdef GL_ES
precision highp float;
#endif

uniform sampler2D u_texture;
varying vec4 v_color;
const float scale = 2.0;

void main()
{
    vec2 uv = floor(gl_FragCoord.xy / scale) + 0.5;
    uv += 1.0 - clamp((1.0 - fract(gl_FragCoord.xy / scale)) * scale, 0.0, 1.0);

    //badlogic.jpg is 256x256
    vec2 texResolution = vec2(256.0, 256.0);

    //vflip
    uv.y = texResolution.y - uv.y;

    gl_FragColor = v_color * texture2D(u_texture, uv / texResolution);
}