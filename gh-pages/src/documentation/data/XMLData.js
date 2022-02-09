import React from "react";
import {MinVersionBadge, RemovedBadge, RequiredBadge} from "../Badges";

//language=XML
const audioUsage = [
  `<Audio id="music.mp3" />`,
  `<Audio id="music.mp3" start-at="00:01:11" stop-at="00:02:11" onTriggered="audioP2()" target="Page3" loops="1" if-set="flag1+flag2" if-not-set="flag3" />`
];


//language=XML
const audio2Usage = [
  `<Audio2 id="music.mp3" />`,
  `<Audio2 id="music.mp3" start-at="00:01:11" stop-at="00:02:11" onTriggered="audioP2()" target="Page3" loops="1" if-set="flag1+flag2" if-not-set="flag3" />`
];

//language=XML
const authorUsage =
[`<Author id="12345">
  <Name>My Name</Name>
  <Url>https://example.com/author</Url>
</Author>`];

//language=XML
const buttonUsage = [
  `<Button target="page(2..10)">Continue</Button>`,
  `<Button target="Saved" onclick="save()" set="flag1,flag2" unset="flag3" if-set="flag4+flag5" if-not-set="flag6">Save</Button>`
];

//language=XML
const cssUsage = [`<CSS>body {color: white;} .red {color: red;}</CSS>`];

//language=XML
const delayUsage = [
  `<Delay target="page(2..10)" seconds="0" />`,
  `<Delay target="page2" seconds="(5..10)" style="normal" start-with="50" onTriggered="advanceTask()" />`,
  `<Delay target="page2" seconds="(5..25)" set="flag1,flag2" unset="flag3" if-set="flag4+flag5" if-not-set="flag6" />`
];

//language=XML
const globalButtonUsage = [
  `<GlobalButton action="add" id="global1" target="start">Restart</GlobalButton>`,
  `<GlobalButton action="remove" id="global1" />`
];

//language=XML
const globalJavascriptUsage = [
  `<GlobalJavascript>
<![CDATA[
    function addRestartButton() {
        overRide.addButton("start", "Restart","", "", "", "");
    }
]]>
</GlobalJavascript>`
];

//language=XML
const includeUsage = [`<Pages>
    <Include file="AdditionalPages.xml" />
    [...]
</Pages>`];

//language=XML
const imageUsage = [`<Image id="img*.jpg" if-set="flag1+flag2" if-not-set="flag3"/>`];

//language=XML
const javascriptUsage = [
  `<javascript>
<![CDATA[
    scriptVars.put("name", "value");
]]>
</javascript>`];

//language=XML
const mediaDirectoryUsage = [`<MediaDirectory>Media</MediaDirectory>`];

//language=XML
const metronomeUsage = [
  `<Metronome bpm="60" />`,
  `<Metronome bpm="60" beats="4" loops="10" rhythm="1,5,9,13,17,21,25,29,33,37,39,41,43,45,47,49,51,53,55,57,59,62,65,69,74,80,81,82,83,84,85,86,87,88,89,90,93,97,98,100,102,104,105,106,126,127,128,129,131,134,139,146" />`
];

//language=XML
const pageUsage = [`<Page id="page1">
    [...]
</Page>`];

//language=XML
const pagesUsage = [`<Pages>
    [...]
</Pages>`];

//language=XML
const prefUsage = [
  `<pref key="pref1" screen="What is preference 1?" type="String" value="Pref One Value" />`,
  `<pref key="pref1" screen="What is preference 1?" type="String" value="Pref One Value" sortOrder="1" />`
];

//language=XML
const settingsUsage = [`<Settings>
    <AutoSetPageWhenSeen>true</AutoSetPageWhenSeen>
    <PageSound>false</PageSound>
    <ForceStartPage>true</ForceStartPage>
    <ConvertArgumentTypes>true</ConvertArgumentTypes>
</Settings>`];

//language=XML
const teaseUsage =
[`<Tease id="12345" scriptVersion="v0.1" minimumVersion="1.2.3">
  [...]
</Tease>`];

const sharedTextUsage = `
  <p>My name is
    <span>myName</span>
  </p>
  <p>
    <u>underlined text</u>
  </p>
  <p>
    <em>
      <b>bold and italic text</b>
    </em>
  </p>
  <p>
    <font color="green">coloured text</font>
  </p>
  <p>
    <font size="+1" style="font-family:'Courier New', Courier, monospace">different font</font>
  </p>
  <p>
    <a href="http://google.com" target="_blank">google.com</a>
  </p>
  <form>
    <p>Choose your sex:</p>
    <input type="radio" name="sex" value="male" checked="checked"/>Male
    <input type="radio" name="sex" value="female"/>Female
    <p>Choose your Toys:</p>
    <input type="checkbox" name="toy1" checked="checked"/>Toy 1
    <input type="checkbox" name="toy2"/>Toy 2
    <p>Choose your Nickname:</p>
    <input type="text" name="nickname" value="My Nickname"/>
    <p>Choose your Difficulty:</p>
    <select name="difficulty">
      <option value="easy">Easy</option>
      <option value="normal">Normal</option>
      <option value="hard">Hard</option>
    </select>
  </form>`;
const textUsage = [`<Text>${sharedTextUsage}</Text>`];
const leftTextUsage = [`<LeftText>${sharedTextUsage}</LeftText>`];

//language=XML
const timerUsage = [
  `<Timer seconds="10" imageId="image1.jpg" />`,
  `<Timer seconds="10" imageId="image1.jpg">This text will appear on the right side</Timer>`
];

//language=XML
const titleUsage = [`<Title>My Tease Title</Title>`];

//language=XML
const urlUsage = [`<Url>https://example.com/myFancyTease</Url>`];

//language=XML
const videoUsage = [
  `<Video id="video.mp4" />`,
  `<Video id="video.flv" start-at="00:01:00" stop-at="00:01:30" onTriggered="videoP6()" target="Page7" />`
];

//language=HTML
const targetFormat = <span>page1<br/>page(1..5)</span>;

const audioAttributes = [
  {
    name: "id",
    required: true,
    format: <span>music1.mp3<br/>music*.mp3<br/>musicDir</span>,
    description:
      <span>Name of the file to play. Allows wildcards (*) and directories. All paths are relative to <code>MediaDirectory</code>.</span>
  },
  {
    name: "if-set",
    required: false,
    format: <span>1<br/>1+2+3<br/>1|2|3</span>,
    description: "Only play this file if the given flags are set. \"+\" indicates AND, \"|\" indicates OR."
  },
  {
    name: "if-not-set",
    required: false,
    format: <span>1<br/>1+2+3<br/>1|2|3</span>,
    description: "Only play this file if the given flags are NOT set. \"+\" indicates AND, \"|\" indicates OR."
  },
  {
    name: "start-at",
    required: false,
    format: "00:01:10",
    description: "Time to start playing the file at."
  },
  {
    name: "stop-at",
    required: false,
    format: "00:02:30",
    description: "Time to stop playing the file at."
  },
  {
    name: "target",
    required: false,
    format: "page1",
    description: "Page to navigate to when this file has finished playing."
  },
  {
    name: "loops",
    required: false,
    format: "2",
    description: "Number of times to play the file."
  },
  {
    name: "onTriggered",
    required: false,
    format: "myFunction()",
    description: "JavaScript action to execute when this file has finished playing."
  },
  {
    name: "if-before",
    required: false,
    format: "13:30",
    description: "Time of day (hh:mm) the audio will only be played before."
  },
  {
    name: "if-after",
    required: false,
    format: "13:30",
    description: "Time of day (hh:mm) the audio will only be played after."
  },
  {
    name: "scriptVar",
    required: false,
    format: "sVar1=Fred,sVar2=10",
    description: "Comma separated list of script variables to set."
  },
  {
    name: "volume",
    required: false,
    format: "100",
    description: "0 to 100, percent of normal volume."
  }
];

const buttonAttributes = [
  {
    name: "target",
    required: true,
    removed: "0.4.2",
    format: targetFormat,
    description: "Page to navigate to when this button is clicked. Ranges are also allowed. Requirement removed in GuideMe v0.4.2."
  },
  {
    name: "target",
    required: false,
    minVersion: "0.4.2",
    format: targetFormat,
    description: "Page to navigate to when this button is clicked. Ranges are also allowed. No longer required as of GuideMe v0.4.2 to allow for JS-only buttons."
  },
  {
    name: "set",
    required: false,
    format: <span>1<br/>1,2,3<br/></span>,
    description: "Flags to set when this button is clicked."
  },
  {
    name: "unset",
    required: false,
    format: <span>1<br/>1,2,3<br/></span>,
    description: "Flags to unset when this button is clicked."
  },
  {
    name: "if-set",
    required: false,
    format: <span>1<br/>1+2+3<br/>1|2|3</span>,
    description: "Only show this button if the given flags are set. \"+\" indicates AND, \"|\" indicates OR."
  },
  {
    name: "if-not-set",
    required: false,
    format: <span>1<br/>1+2+3<br/>1|2|3</span>,
    description: "Only show this button if the given flags are NOT set. \"+\" indicates AND, \"|\" indicates OR."
  },
  {
    name: "if-before",
    required: false,
    format: "13:30",
    description: "Time of day (hh:mm) the button will only be shown before."
  },
  {
    name: "if-after",
    required: false,
    format: "13:30",
    description: "Time of day (hh:mm) the button will only be shown after."
  },
  {
    name: "onclick",
    required: false,
    format: "myFunction()",
    description: "JavaScript action to execute when this button is clicked."
  },
  {
    name: "image",
    required: false,
    format: "btn1.jpg",
    description: "Image to display on the button."
  },
  {
    name: "hotkey",
    required: false,
    format: "k",
    description: "Hotkey to bind the button to."
  },
  {
    name: "scriptVar",
    required: false,
    format: "sVar1=Fred,sVar2=10",
    description: "Comma separated list of script variables to set."
  },
  {
    name: "fontName",
    required: false,
    format: "Arial",
    description: "Font name to use for button text."
  },
  {
    name: "fontHeight",
    required: false,
    format: "12",
    description: "Size of the button text."
  },
  {
    name: "bgColor1",
    required: false,
    format: "red",
    description: "Background color."
  },
  {
    name: "bgColor2",
    required: false,
    format: "blue",
    description: "Second background color for graduated fill."
  },
  {
    name: "fontColor",
    required: false,
    format: "black",
    description: "Color of the font in the button."
  },
  {
    name: "sortOrder",
    required: false,
    format: "1",
    description: "Sort value. Allows buttons on the page to be sorted."
  },
  {
    name: "disabled",
    required: false,
    format: "false",
    description: "set true to disable the button on page load."
  },
  {
    name: "id",
    required: false,
    format: "btnOne",
    description: "ID of the button to use when referencing from JavaScript."
  }
];

const globalButtonAttributes = [
  {
    name: "id",
    required: true,
    format: "btnOne",
    description: "ID of the button to use when referencing from JavaScript."
  },
  {
    name: "action",
    required: true,
    format: <span>add<br/>remove</span>,
    description: "Whether you are adding or removing this button object. If not provided, button will be ignored."
  },
  {
    name: "placement",
    required: false,
    format: <span>top<br/>bottom</span>,
    description: "Whether to place this button at the top or bottom of all other buttons. Defaults to bottom."
  },
  {
    name: "target",
    required: false,
    format: targetFormat,
    description: "Page to navigate to when this button is clicked. Ranges are also allowed. No longer required as of GuideMe v0.4.2 to allow for JS-only buttons."
  },
  {
    name: "set",
    required: false,
    format: <span>1<br/>1,2,3<br/></span>,
    description: "Flags to set when this button is clicked."
  },
  {
    name: "unset",
    required: false,
    format: <span>1<br/>1,2,3<br/></span>,
    description: "Flags to unset when this button is clicked."
  },
  {
    name: "if-set",
    required: false,
    format: <span>1<br/>1+2+3<br/>1|2|3</span>,
    description: "Only show this button if the given flags are set. \"+\" indicates AND, \"|\" indicates OR."
  },
  {
    name: "if-not-set",
    required: false,
    format: <span>1<br/>1+2+3<br/>1|2|3</span>,
    description: "Only show this button if the given flags are NOT set. \"+\" indicates AND, \"|\" indicates OR."
  },
  {
    name: "if-before",
    required: false,
    format: "13:30",
    description: "Time of day (hh:mm) the button will only be shown before."
  },
  {
    name: "if-after",
    required: false,
    format: "13:30",
    description: "Time of day (hh:mm) the button will only be shown after."
  },
  {
    name: "onclick",
    required: false,
    format: "myFunction()",
    description: "JavaScript action to execute when this button is clicked."
  },
  {
    name: "image",
    required: false,
    format: "btn1.jpg",
    description: "Image to display on the button."
  },
  {
    name: "hotkey",
    required: false,
    format: "k",
    description: "Hotkey to bind the button to."
  },
  {
    name: "scriptVar",
    required: false,
    format: "sVar1=Fred,sVar2=10",
    description: "Comma separated list of script variables to set."
  },
  {
    name: "fontName",
    required: false,
    format: "Arial",
    description: "Font name to use for button text."
  },
  {
    name: "fontHeight",
    required: false,
    format: "12",
    description: "Size of the button text."
  },
  {
    name: "bgColor1",
    required: false,
    format: "red",
    description: "Background color."
  },
  {
    name: "bgColor2",
    required: false,
    format: "blue",
    description: "Second background color for graduated fill."
  },
  {
    name: "fontColor",
    required: false,
    format: "black",
    description: "Color of the font in the button."
  },
  {
    name: "sortOrder",
    required: false,
    format: "1",
    description: "Sort value. Sorting occurs within other Global Buttons with the same placement."
  },
  {
    name: "disabled",
    required: false,
    format: "false",
    description: "set true to disable the button on page load."
  }
];

export const XmlData = [
  {
    name: "Tease",
    required: true,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "N/A",
    min: 1,
    max: 1,
    description: <>
      <p className="lead">Primary wrapper node for a tease.</p>
      <p>This node must be included once and only once in evey tease as the root XML node.</p>
    </>,
    usage: teaseUsage,
    attributes: [
      {
        name: "id",
        required: false,
        format: "12345",
        description: "Tease ID when downloaded from Milovana. Not required, but helpful to tell users the origin."
      },
      {
        name: "scriptVersion",
        required: false,
        minVersion: null,
        deprecated: false,
        removed: null,
        format: "v0.1",
        description: "XML script version. Legacy attribute. Always set to \"v0.1\"if present."
      },
      {
        name: "minimumVersion",
        required: false,
        minVersion: "0.4.2",
        deprecated: false,
        removed: null,
        format: "0.4.2",
        description: "Minimum version of GuideMe this script supports. No \"v\" prefix!"
      }
    ]
  },
  {
    name: "Title",
    required: true,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Tease",
    min: 1,
    max: 1,
    description: <>
      <p className="lead">The display name of the tease. Shown at the top of the screen.</p>
    </>,
    usage: titleUsage,
    attributes: []
  },
  {
    name: "Url",
    required: false,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Tease",
    min: 0,
    max: 1,
    description: <>
      <p className="lead">URL that the tease was download from or can be found at.</p>
    </>,
    usage: urlUsage,
    attributes: []
  },
  {
    name: "Author",
    required: true,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Tease",
    min: 1,
    max: 1,
    description: <>
      <p className="lead">Information about the author of the tease.</p>
      <ul>
        <li><b>Name</b>: Name of the author <RequiredBadge required="true"/></li>
        <li><b>Url</b>: URL for the author <RequiredBadge/></li>
      </ul>
    </>,
    usage: authorUsage,
    attributes: [
      {
        name: "id",
        required: false,
        deprecated: true,
        format: "12345",
        description: "ID of the author from Milovana. Used for backwards compatibility with TeaseMe."
      },
    ]
  },
  {
    name: "MediaDirectory",
    required: true,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Tease",
    min: 1,
    max: 1,
    description: <>
      <p className="lead">Subdirectory that the media files can be found at. Relative to the current XML file.</p>
    </>,
    usage: mediaDirectoryUsage,
    attributes: []
  },
  {
    name: "Audio",
    required: false,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Page",
    min: 0,
    max: 1,
    description: <>
      <p className="lead">Play an audio file using the Audio player.</p>
    </>,
    usage: audioUsage,
    attributes: audioAttributes
  },
  {
    name: "Audio2",
    required: false,
    minVersion: "0.4.0",
    deprecated: false,
    removed: null,
    parents: "Page",
    min: 0,
    max: 1,
    description: <>
        <p className="lead">Play an audio file using the Audio2 player.</p>
        <p>Audio2 is functionally identical
          to <code>Audio</code> but allows for a
          second concurrent audio file to be played, potentially with a different output device.</p>
      </>,
      usage: audio2Usage,
      attributes: audioAttributes
    },
  {
    name: "Button",
    required: false,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Page",
    min: 0,
    max: <span>&infin;</span>,
    description: <>
      <p className="lead">Adds a button to the page.</p>
    </>,
    usage: buttonUsage,
    attributes: buttonAttributes
  },
  {
    name: "GlobalButton",
    required: false,
    minVersion: "0.4.2",
    deprecated: false,
    removed: null,
    parents: "Page",
    min: 0,
    max: <span>&infin;</span>,
    description: <>
      <p className="lead">Adds a button to every page.</p>
    </>,
    usage: globalButtonUsage,
    attributes: globalButtonAttributes
  },
  {
    name: "CSS",
    required: false,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Tease",
    min: 0,
    max: 1,
    description: <>
      <p className="lead">Adds global CSS that can be used in <code>Text</code> nodes.</p>
    </>,
    usage: cssUsage,
    attributes: []
  },
  {
    name: "Delay",
    required: false,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Page",
    min: 0,
    max: 1,
    description: <>
      <p className="lead">Adds a delay (countdown) to a page.</p>
    </>,
    usage: delayUsage,
    attributes: [
      {
        name: "target",
        required: true,
        format: targetFormat,
        description: "Page to navigate to when delay reaches zero. Ranges are also allowed."
      },
      {
        name: "seconds",
        required: true,
        format: <span>10<br/>(5..10)</span>,
        description: "Length of the delay in seconds. Ranges are also allowed."
      },
      {
        name: "style",
        required: false,
        format: "normal",
        description: <span>Style of delay. <code>normal</code> (default), <code>secret</code> (question marks), <code>hidden</code> (invisible).</span>
      },
      {
        name: "start-with",
        required: false,
        format: "5",
        description: "Starts the delay with the given number of seconds. length is not changed."
      },
      {
        name: "set",
        required: false,
        format: <span>1<br/>1,2,3<br/></span>,
        description: "Flags to set when delay reaches zero."
      },
      {
        name: "unset",
        required: false,
        format: <span>1<br/>1,2,3<br/></span>,
        description: "Flags to unset when delay reaches zero."
      },
      {
        name: "if-set",
        required: false,
        format: <span>1<br/>1+2+3<br/>1|2|3</span>,
        description: "Delay is only added if the given flags are set. \"+\" indicates AND, \"|\" indicates OR."
      },
      {
        name: "if-not-set",
        required: false,
        format: <span>1<br/>1+2+3<br/>1|2|3</span>,
        description: "Delay is only added if the given flags are NOT set. \"+\" indicates AND, \"|\" indicates OR."
      },
      {
        name: "if-before",
        required: false,
        format: "13:30",
        description: "Time of day (hh:mm) the delay will only be used before."
      },
      {
        name: "if-after",
        required: false,
        format: "13:30",
        description: "Time of day (hh:mm) the delay will only be used after."
      },
      {
        name: "scriptVar",
        required: false,
        format: "sVar1=Fred,sVar2=10",
        description: "Comma separated list of script variables to set."
      },
      {
        name: "onTriggered",
        required: false,
        format: "myFunction()",
        description: "JavaScript action to execute when delay reaches zero."
      }
    ]
  },
  {
    name: "GlobalJavascript",
    required: false,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Tease",
    min: 0,
    max: 1,
    description: <>
      <p className="lead">Adds the provided JavaScript to every page.</p>
      <p>This is ideal for global function, or functions you want to reuse on multiple pages.</p>
    </>,
    usage: globalJavascriptUsage,
    attributes: []
  },
  {
    name: "Image",
    required: false,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Page",
    min: 0,
    max: 1,
    description: <>
      <p className="lead">Adds an image to the page.</p>
    </>,
    usage: imageUsage,
    attributes: [
      {
        name: "id",
        required: true,
        description: <span>Path to the image relative to the Media Directory. Wildcard character <code>*</code> is allowed.</span>,
        format: <span>img1.jpg<br/>sub1/img1.jpg<br/>img*.jpg</span>
      },
      {
        name: "if-set",
        required: false,
        format: <span>1<br/>1+2+3<br/>1|2|3</span>,
        description: "Image is only added if the given flags are set. \"+\" indicates AND, \"|\" indicates OR."
      },
      {
        name: "if-not-set",
        required: false,
        format: <span>1<br/>1+2+3<br/>1|2|3</span>,
        description: "Image is only added if the given flags are NOT set. \"+\" indicates AND, \"|\" indicates OR."
      },
      {
        name: "if-before",
        required: false,
        format: "13:30",
        description: "Time of day (hh:mm) the image will only be shown before."
      },
      {
        name: "if-after",
        required: false,
        format: "13:30",
        description: "Time of day (hh:mm) the image will only be shown after."
      },
    ]
  },
  {
    name: "Include",
    required: false,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Pages",
    min: 0,
    max: <span>&infin;</span>,
    description: <>
      <p className="lead">Includes an additional XML file.</p>
      <p>The included file must be in the Media Directory.</p>
    </>,
    usage: includeUsage,
    attributes: [
      {
        name: "file",
        required: true,
        format: "AdditionalPages.xml",
        description: "Name of the file to include, relative to the Media Directory."
      }
    ]
  },
  {
    name: "Javascript",
    required: false,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Page",
    min: 0,
    max: 1,
    description: <>
      <p className="lead">Includes JavaScript on the current page.</p>
      <p>Please note, this tag is lowercase!</p>
    </>,
    usage: javascriptUsage,
    attributes: []
  },
  {
    name: "Metronome",
    required: false,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Page",
    min: 0,
    max: 1,
    description: <>
      <p className="lead">Plays a metronome sound.</p>
    </>,
    usage: metronomeUsage,
    attributes: [
      {
        name: "bpm",
        required: true,
        format: "60",
        description: "Beats per minute (absolute or used to set tempo for rhythm."
      },
      {
        name: "beats",
        required: false,
        format: "4",
        description: "Only used with Rhythm. Number of sub beats."
      },
      {
        name: "loops",
        required: false,
        format: "10",
        description: "Only used with Rhythm. Number of times to loop the rhythm."
      },
      {
        name: "rhythm",
        required: false,
        format: "1,5,9,13",
        description: <span>Rhythm in sub beats. If bpm=60 and beats=4, we have 1 bar per second with 4 beats per bar, so a beat every 0.25 seconds.<br/>
          If rhythm="1,5,9,13" we get 4 clicks once per second.<br/>
          If rhythm="1,3,5,7,9,11,13" we get 7 clicks once every half second.</span>
      },
      {
        name: "if-set",
        required: false,
        format: <span>1<br/>1+2+3<br/>1|2|3</span>,
        description: "Only enable if the given flags are set. \"+\" indicates AND, \"|\" indicates OR."
      },
      {
        name: "if-not-set",
        required: false,
        format: <span>1<br/>1+2+3<br/>1|2|3</span>,
        description: "Only enable if the given flags are NOT set. \"+\" indicates AND, \"|\" indicates OR."
      },
      {
        name: "if-before",
        required: false,
        format: "13:30",
        description: "Time of day (hh:mm) the metronome will only be enabled before."
      },
      {
        name: "if-after",
        required: false,
        format: "13:30",
        description: "Time of day (hh:mm) the metronome will only be enabled after."
      },
    ]
  },
  {
    name: "Page",
    required: true,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Pages",
    min: 1,
    max: <span>&infin;</span>,
    description: <>
      <p className="lead">Base node to define a page.</p>
    </>,
    usage: pageUsage,
    attributes: [
      {
        name: "id",
        required: true,
        format: "page1",
        description: "ID of the page. Must be unique across all pages!"
      }
    ]
  },
  {
    name: "Pages",
    required: true,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Tease",
    min: 1,
    max: 1,
    description: <>
      <p className="lead">Wrapper for all pages.</p>
    </>,
    usage: pagesUsage,
    attributes: []
  },
  {
    name: "Settings",
    required: false,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Tease",
    min: 0,
    max: 1,
    description: <>
      <p className="lead">Settings for the tease.</p>
      <ul>
        <li><b>AutoSetPageWhenSeen</b>: Adds the page name as a flag when the page is viewed. <RequiredBadge/></li>
        <li><b>PageSound</b>: Play the page change sound when changing pages. <RequiredBadge/></li>
        <li><b>ForceStartPage</b>: Force the guide to start on the "start" page every time it is loaded. <RequiredBadge/></li>
        <li><b>ConvertArgumentTypes</b>: Convert arguments from javascript called by buttons, timers, etc. to non-string types when appropriate. <RequiredBadge/></li>
      </ul>
    </>,
    usage: settingsUsage,
    attributes: []
  },
  {
    name: "Pref",
    required: false,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Tease",
    min: 0,
    max: <span>&infin;</span>,
    description: <>
      <p className="lead">Guide preferences.</p>
      <p>Shown in the Guide Preferences screen, and editable via JavaScript.</p>
      <p>Please note, this tag is lowercase!</p>
    </>,
    usage: prefUsage,
    attributes: [
      {
        name: "key",
        required: true,
        format: "key",
        description: "Variable name"
      },
      {
        name: "type",
        required: true,
        format: <span>boolean<br/>string<br/>number</span>,
        description: <span>Data type of variable. Valid values are <code>boolean</code>, <code>string</code>, and <code>number</code>.</span>
      },
      {
        name: "screen",
        required: true,
        format: "My Setting",
        description: "Display name of this preference in the preferences menu."
      },
      {
        name: "value",
        required: true,
        format: "value",
        description: "Default value to use if not set be user."
      },
      {
        name: "sortOrder",
        required: false,
        format: "1",
        description: "Order this preference will appear in the preferences menu."
      }
    ]
  },
  {
    name: "Text",
    required: false,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Page",
    min: 0,
    max: <span>1 <RemovedBadge version="0.4.0"/><br/>&infin; <MinVersionBadge version="0.4.0"/></span>,
    description: <>
      <p className="lead">Text to show on the right pane.</p>
      <p>Supports HTML and HTML Forms. Use <code>&lt;span&gt;</code> to display JavaScript variables.</p>
    </>,
    usage: textUsage,
    attributes: [
      {
        name: "if-set",
        required: false,
        minVersion: "0.4.0",
        format: <span>1<br/>1+2+3<br/>1|2|3</span>,
        description: "Only show this text if the given flags are set. \"+\" indicates AND, \"|\" indicates OR."
      },
      {
        name: "if-not-set",
        required: false,
        minVersion: "0.4.0",
        format: <span>1<br/>1+2+3<br/>1|2|3</span>,
        description: "Only show this text if the given flags are NOT set. \"+\" indicates AND, \"|\" indicates OR."
      },
      {
        name: "if-before",
        required: false,
        minVersion: "0.4.0",
        format: "13:30",
        description: "Time of day (hh:mm) the text will only be shown before."
      },
      {
        name: "if-after",
        required: false,
        minVersion: "0.4.0",
        format: "13:30",
        description: "Time of day (hh:mm) the text will only be shown after."
      }
    ]
  },
  {
    name: "LeftText",
    required: false,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Page",
    min: 0,
    max: 1,
    description: <>
      <p className="lead">Text to show on the left pane instead of an image or video.</p>
      <p>Supports HTML and HTML Forms. Use <code>&lt;span&gt;</code> to display JavaScript variables.</p>
    </>,
    usage: leftTextUsage,
    attributes: []
  },
  {
    name: "Timer",
    required: false,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Page",
    min: 0,
    max: <span>&infin;</span>,
    description: <>
      <p>
        Creates a timer which results in an action after a number of seconds.
        Similar to a delay but designed to update the current page not go to a new one.
      </p>
      <p>Text within the node will display in the right text pane.</p>
    </>,
    usage: timerUsage,
    attributes: [
      {
        name: "seconds",
        required: true,
        format: <span>10<br/>(5..10)</span>,
        description: <span>Number of seconds for the timer.</span>
      },
      {
        name: "seconds",
        required: false,
        minVersion: "0.4.5",
        format: <span>10<br/>(5..10)</span>,
        description: <span>Number of seconds for the timer.</span>
      },
      {
        name: "if-set",
        required: false,
        format: <span>1<br/>1+2+3<br/>1|2|3</span>,
        description: "Only run this timer if the given flags are set. \"+\" indicates AND, \"|\" indicates OR."
      },
      {
        name: "if-not-set",
        required: false,
        format: <span>1<br/>1+2+3<br/>1|2|3</span>,
        description: "Only run this timer if the given flags are NOT set. \"+\" indicates AND, \"|\" indicates OR."
      },
      {
        name: "if-before",
        required: false,
        format: "13:30",
        description: "Time of day (hh:mm) the timer will only be run before."
      },
      {
        name: "if-after",
        required: false,
        format: "13:30",
        description: "Time of day (hh:mm) the timer will only be run after."
      },
      {
        name: "set",
        required: false,
        format: <span>1<br/>1,2,3<br/></span>,
        description: "Flags to set when timer reaches zero."
      },
      {
        name: "unset",
        required: false,
        format: <span>1<br/>1,2,3<br/></span>,
        description: "Flags to unset when timer reaches zero."
      },
      {
        name: "imageId",
        required: false,
        format: "image1.jpg",
        description: "Image to change to when the timer reaches zero."
      },
      {
        name: "id",
        required: false,
        format: "1",
        description: "ID of the timer. Used to re-run this timer."
      },
      {
        name: "onTriggered",
        required: false,
        format: "myFunction()",
        description: "JavaScript action to execute when the timer reaches zero."
      },
    ]
  },
  {
    name: "Video",
    required: false,
    minVersion: null,
    deprecated: false,
    removed: null,
    parents: "Page",
    min: 0,
    max: <span>&infin;</span>,
    description: <>
      <p className="lead">Video to show.</p>
      <p>If more than one video is provided, the first valid one will be played.</p>
    </>,
    usage: videoUsage,
    attributes: [
      {
        name: "id",
        required: true,
        format: <span>video1.mp4<br/>video*.mp4<br/>videoDir</span>,
        description:
          <span>Name of the file to play. Allows wildcards (*) and directories. All paths are relative to <code>MediaDirectory</code>.</span>
      },
      {
        name: "target",
        required: false,
        format: "page1",
        description: "Page to navigate to when this file has finished playing."
      },
      {
        name: "start-at",
        required: false,
        format: "00:01:10",
        description: "Time to start playing the file at."
      },
      {
        name: "stop-at",
        required: false,
        format: "00:02:30",
        description: "Time to stop playing the file at."
      },
      {
        name: "if-set",
        required: false,
        format: <span>1<br/>1+2+3<br/>1|2|3</span>,
        description: "Only play this file if the given flags are set. \"+\" indicates AND, \"|\" indicates OR."
      },
      {
        name: "if-not-set",
        required: false,
        format: <span>1<br/>1+2+3<br/>1|2|3</span>,
        description: "Only play this file if the given flags are NOT set. \"+\" indicates AND, \"|\" indicates OR."
      },
      {
        name: "if-before",
        required: false,
        format: "13:30",
        description: "Time of day (hh:mm) the video will only be played before."
      },
      {
        name: "if-after",
        required: false,
        format: "13:30",
        description: "Time of day (hh:mm) the video will only be played after."
      },
      {
        name: "loops",
        required: false,
        format: "2",
        description: "Number of times to play the file."
      },
      {
        name: "onTriggered",
        required: false,
        format: "myFunction()",
        description: "JavaScript action to execute when this file has finished playing."
      },
      {
        name: "scriptVar",
        required: false,
        format: "sVar1=Fred,sVar2=10",
        description: "Comma separated list of script variables to set."
      }
    ]
  }
];