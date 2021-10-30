# scad-clj example project

_Interactive 3D Modeling with Clojure_

[OpenSCAD](https://openscad.org) + [scad-clj](https://github.com/farrellm/scad-clj) + [Calva](https://calva.io) => Smooth and enjoyable **Interactive 3D Modelling**.

[![](example.png?raw=true)](https://youtube.com/watch?fJpDztSR53E)

There is [a demo of the project on YouTube](https://youtu.be/fJpDztSR53E). Please consider to subscribe to the CalvaTV channel! (Sorry about the somewhat extra amateur-ish production, my recording software was acting up on me, big time.)

## Install

It is very easy to set up. The software stack is Java, the [clojure](https://clojure.org/) tool, scad-clj and OpenSCAD. scad-clj is a wrapper/transpiler from Clojure to `.scad`. It makes it super easy to compose in 3D space. OpenSCAD shows you the results immediately, in an incredibly easy to use viewer. I am (of course) using Calva in [VS Code](https://code.visualstudio.com).

This project includes scad-clj as a submodule. Thus, in addition to installing OpenSCAD you'll ”install” scad-clj together with this project:

1. Click `Use this template` to create a copy of this repository in your own GitHub account.
1. Clone the repository: `git clone <url>`
1. Initialise and download the `scad-clj` submodule: `git submodule update --init --recursive`

## Get Started

With those things installed on your computer, you'll be interactively rendering 3D models from Clojure code in something like one minute:

1. Start OpenSCAD
1. Open the project in VS Code and arrange for the VS Code and OpenSCAD windows to show the render view of OpenSCAD while you are working in VS Code.
1. Start the project, **Calva: Start a Project REPL and Connect (aka Jack-in)**
1. Open an example file, say [extender.clj](src/scad_clj_workflow/hydroponics/extender.clj)
1. Load the file, **Calva: Load Current File and Dependencies**
   * This creates the `render.scad` file at the root of the project.
1. In OpenSCAD, open `render.scad`
    * This will render the model and OpenSCAD will hot reload it every time it updates.

## Render away!

Now you are set! All the examples, except [example.clj](src/scad_clj_workflow/example.clj) will re-create the `render.scad` file when you load it in the REPL (like in step 6 above). You will see the updated model rendered in OpenSCAD immediately.

`example.clj` is a bit different:

* It hides the `(render (model))` evaluation in a `(comment ...)` (a.k.a. [Rich Comment](https://calva.io/rich-comments/)) form, which makes it **not** run when the file loads.

But... why? Isn't it super convenient to just load the file and rerender the model? Yes, it is. However, it is an anti-pattern, imo, to have side effects and potentially compute heavy things, to happen on file load, if it can be avoided. You want to be able to load the file to modify the running program, without worrying about mutating the world around you. Terrforming should be an explicit action. Therefore:

### Customized workflow settings included

This project includes some VS Code settings that makes it easy to render any sub component of the full model:

1. Render the current form with `ctrl+alt+space c`
   * With the cursor right before or after e.g. `(m/cube 5 5 1))` you'll render just that ”cube”.
1. Render the current function with `ctrl+alt+space f`
   * With the cursor somewhere inside a zero-argument function, like:

   ```clojure
   (defn name-label []
     (->> (m/text "Calva ♥️ scad-clj" :size text-s
            :halign "center" :valign "center")
          (m/color text-color)
          (m/translate [0 0 text-t])))
   ```

   You'll render just what that function renders.
1. Render the full model with `ctrl+alt+space m`
   * With the cursor anywhere in the file, this will render the full model.

To make your own models compliant with this workflow you will need to:

* Name your main model function `model`
* Make all functions that you want to render in isolation take no arguments.

To facilitate this, `example.clj` uses namespace `def`s for all configuration of the model. It also wraps these `def`s in a `(do ...)` form to make them all be redefined at the press of `alt+enter` (**Evaluate Top Level form**), since they sometimes are derived from each other.

You can make explicit testing functions that run other functions providing them with arguments, when zero arguments just don't make sense.

Check [settings.json](.vscode/settings.json) to see how these shortcuts are defined. See [Custom REPL Commands](https://calva.io/custom-commands/) for how to create more of these workflow enhancements yourself.

## Documentation

OpenSCAD is [well documented](https://openscad.org/documentation.html) and there are a lot of resources out there.

scad-clj isn't all that documented yet (not at all, actually), but don't fear! Mostly you will figure things out with the help of the OpenSCAD documentation and the examples in this project. As it happens, the scad-clj source code is included in this project, and digging around some there will inform you how the OpenSCAD documentation translates to Clojure forms. _Also see below about the `#scad-clj` Slack channel_.

Calva's user guide is at [calva.io](https://calva.io).

Clojure is documented at [clojure.org](https://clojure.org), there are super nice examples of how to use the extensive Clojure core library at [clojuredocs.org](https://clojuredocs.org) (and [soon those will also be available in Calva](https://twitter.com/pappapez/status/1452409528511762444)). If you are new to Clojure I can happily recommend my [Get Started with Clojure](https://calva.io/get-started-with-clojure/) guide.

## Feedback, questions

The quickest and best way is to join the [#scad-clj](https://clojurians.slack.com/archives/C02K1D8V6CB) channel on the [Clojurians Slack](https://clojurians.net). In `#scad-clj` experts and beginners enthusiastically share tips and help each other. You are super welcome, and you'll love it.

Please ask questions and provide tips or whatever in the comments to the YouTube video demonstrating this projecy. (See above.)

By all means, please feel invited to use the issues to ask for help.

Pull Requests are entirely welcome!

## Thanks to my sponsors  ❤️

I want to thank my employer [Agical](https://agical.se) who pays me work one day a week with Calva.

And thanks to my [Github Sponsors](https://github.com/sponsors/PEZ), please join them!

## About the examples:

Most of them are @stianeikeland's. This project started as a copy of his [Clojure CSG experiments](https://github.com/stianeikeland/clojurescad-experiments) repository.

## Why scad-clj as a submodule?

There are changes in latest scad-clj `master` that I want available, which are not available in the latest release cut.

## License

See LICENSE.md file. Stian Eikeland choose to use EPL, and I'm not sure if I can just change that, so keeping. But as far as my contributions go, think of them as being fully in the Public Domain, use them in any way you fancy, at your own risks (should be minor, 😄). If you like to credit me, I will be super happy you did.
